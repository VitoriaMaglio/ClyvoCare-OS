package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.TutorRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.TutorResponseDTO;
import br.com.clyvocare.clyvocare_api.service.TutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
@RequiredArgsConstructor
@Tag(name = "Tutores", description = "Gerenciamento de tutores (proprietários dos pets)")
public class TutorController {

    private final TutorService tutorService;

    // -------------------------------------------------------
    // POST /tutores
    // -------------------------------------------------------
    @PostMapping
    @Operation(summary = "Cadastra um novo tutor")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Tutor criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "422", description = "E-mail ou CPF já cadastrado")
    })
    public ResponseEntity<TutorResponseDTO> criar(@RequestBody @Valid TutorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tutorService.criar(dto));
    }

    // -------------------------------------------------------
    // GET /tutores
    // Suporta paginação, ordenação e busca por nome e estado
    // Exemplos:
    //   GET /tutores?page=0&size=10&sort=nome,asc
    //   GET /tutores?nome=Maria
    //   GET /tutores?estado=SP
    // -------------------------------------------------------
    @GetMapping
    @Operation(summary = "Lista tutores com paginação, ordenação e filtros opcionais",
               description = "Parâmetros: nome (busca parcial), estado (UF). " +
                             "Suporta paginação (?page=0&size=10) e ordenação (?sort=nome,asc)")
    public ResponseEntity<Page<TutorResponseDTO>> listar(
            @Parameter(description = "Filtro por nome (busca parcial, opcional)")
            @RequestParam(required = false) String nome,

            @Parameter(description = "Filtro por estado UF (opcional)", example = "SP")
            @RequestParam(required = false) String estado,

            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC)
            Pageable pageable) {

        if (nome != null && !nome.isBlank()) {
            return ResponseEntity.ok(tutorService.buscarPorNome(nome, pageable));
        }
        if (estado != null && !estado.isBlank()) {
            return ResponseEntity.ok(tutorService.buscarPorEstado(estado, pageable));
        }
        return ResponseEntity.ok(tutorService.listar(pageable));
    }

    // -------------------------------------------------------
    // GET /tutores/{id}
    // -------------------------------------------------------
    @GetMapping("/{id}")
    @Operation(summary = "Busca tutor por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tutor encontrado"),
        @ApiResponse(responseCode = "404", description = "Tutor não encontrado")
    })
    public ResponseEntity<TutorResponseDTO> buscarPorId(
            @Parameter(description = "ID do tutor", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(tutorService.buscarPorId(id));
    }

    // -------------------------------------------------------
    // PUT /tutores/{id}
    // -------------------------------------------------------
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza dados do tutor")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tutor atualizado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Tutor não encontrado"),
        @ApiResponse(responseCode = "422", description = "E-mail ou CPF já em uso por outro tutor")
    })
    public ResponseEntity<TutorResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid TutorRequestDTO dto) {
        return ResponseEntity.ok(tutorService.atualizar(id, dto));
    }

    // -------------------------------------------------------
    // DELETE /tutores/{id}
    // -------------------------------------------------------
    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um tutor",
               description = "Não permite exclusão se o tutor possuir pets cadastrados")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Tutor removido"),
        @ApiResponse(responseCode = "404", description = "Tutor não encontrado"),
        @ApiResponse(responseCode = "422", description = "Tutor possui pets e não pode ser excluído")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do tutor", example = "1")
            @PathVariable Long id) {
        tutorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
