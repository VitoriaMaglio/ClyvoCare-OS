package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.VeterinarioRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.VeterinarioResponseDTO;
import br.com.clyvocare.clyvocare_api.service.VeterinarioService;
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
@RequestMapping("/veterinarios")
@RequiredArgsConstructor
@Tag(name = "Veterinários", description = "Gerenciamento de veterinários")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @PostMapping
    @Operation(summary = "Cadastra um novo veterinário")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Veterinário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "422", description = "CRMV já cadastrado")
    })
    public ResponseEntity<VeterinarioResponseDTO> criar(@RequestBody @Valid VeterinarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veterinarioService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista veterinários com paginação, ordenação e filtros",
               description = "Filtros: nome, especialidade, idClinica")
    public ResponseEntity<Page<VeterinarioResponseDTO>> listar(
            @Parameter(description = "Filtro por nome") @RequestParam(required = false) String nome,
            @Parameter(description = "Filtro por especialidade", example = "Cardiologia") @RequestParam(required = false) String especialidade,
            @Parameter(description = "Filtro por ID da clínica") @RequestParam(required = false) Long idClinica,
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {

        if (nome != null && !nome.isBlank()) {
            return ResponseEntity.ok(veterinarioService.buscarPorNome(nome, pageable));
        }
        if (especialidade != null && !especialidade.isBlank()) {
            return ResponseEntity.ok(veterinarioService.buscarPorEspecialidade(especialidade, pageable));
        }
        if (idClinica != null) {
            return ResponseEntity.ok(veterinarioService.buscarPorClinica(idClinica, pageable));
        }
        return ResponseEntity.ok(veterinarioService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca veterinário por ID")
    public ResponseEntity<VeterinarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(veterinarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza dados do veterinário")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Veterinário atualizado"),
        @ApiResponse(responseCode = "404", description = "Veterinário não encontrado"),
        @ApiResponse(responseCode = "422", description = "CRMV já em uso")
    })
    public ResponseEntity<VeterinarioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid VeterinarioRequestDTO dto) {
        return ResponseEntity.ok(veterinarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um veterinário",
               description = "Não permite exclusão se o veterinário possuir consultas registradas")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Veterinário removido"),
        @ApiResponse(responseCode = "404", description = "Veterinário não encontrado"),
        @ApiResponse(responseCode = "422", description = "Veterinário possui consultas e não pode ser excluído")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        veterinarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
