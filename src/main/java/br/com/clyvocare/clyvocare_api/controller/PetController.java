package br.com.clyvocare.controller;

import br.com.clyvocare.dto.request.PetRequestDTO;
import br.com.clyvocare.dto.response.*;
import br.com.clyvocare.service.PetService;
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

import java.util.List;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
@Tag(name = "Pets", description = "Gerenciamento de pets e funcionalidades inteligentes de saúde")
public class PetController {

    private final PetService petService;

    @PostMapping
    @Operation(summary = "Cadastra um novo pet")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pet criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "422", description = "Microchip já cadastrado ou raça incompatível com espécie")
    })
    public ResponseEntity<PetResponseDTO> criar(@RequestBody @Valid PetRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista pets com paginação, ordenação e filtros",
               description = "Filtros disponíveis: nome (busca parcial), especie (nome), idTutor. " +
                             "Suporta paginação (?page=0&size=10) e ordenação (?sort=nome,asc)")
    public ResponseEntity<Page<PetResponseDTO>> listar(
            @Parameter(description = "Filtro por nome do pet") @RequestParam(required = false) String nome,
            @Parameter(description = "Filtro por espécie", example = "Cão") @RequestParam(required = false) String especie,
            @Parameter(description = "Filtro por ID do tutor") @RequestParam(required = false) Long idTutor,
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {

        if (nome != null && !nome.isBlank()) {
            return ResponseEntity.ok(petService.buscarPorNome(nome, pageable));
        }
        if (especie != null && !especie.isBlank()) {
            return ResponseEntity.ok(petService.buscarPorEspecie(especie, pageable));
        }
        if (idTutor != null) {
            return ResponseEntity.ok(petService.buscarPorTutor(idTutor, pageable));
        }
        return ResponseEntity.ok(petService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca pet por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pet encontrado"),
        @ApiResponse(responseCode = "404", description = "Pet não encontrado")
    })
    public ResponseEntity<PetResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(petService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza dados do pet")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pet atualizado"),
        @ApiResponse(responseCode = "404", description = "Pet não encontrado"),
        @ApiResponse(responseCode = "422", description = "Microchip duplicado ou raça incompatível")
    })
    public ResponseEntity<PetResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PetRequestDTO dto) {
        return ResponseEntity.ok(petService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um pet",
               description = "Não permite exclusão se o pet possuir histórico clínico")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Pet removido"),
        @ApiResponse(responseCode = "404", description = "Pet não encontrado"),
        @ApiResponse(responseCode = "422", description = "Pet possui histórico e não pode ser excluído")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        petService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------------------------------------------
    // DIFERENCIAIS INTELIGENTES
    // -------------------------------------------------------

    @GetMapping("/{id}/risco")
    @Operation(summary = "Calcula o score de risco de saúde do pet",
               description = "Avalia vacinas atrasadas, tratamentos abandonados, frequência de consultas e fase de vida")
    public ResponseEntity<RiscoResponseDTO> calcularRisco(@PathVariable Long id) {
        return ResponseEntity.ok(petService.calcularRisco(id));
    }

    @GetMapping("/{id}/alertas")
    @Operation(summary = "Gera alertas preventivos automáticos para o pet",
               description = "Retorna lista de alertas com base no estado clínico atual do animal")
    public ResponseEntity<List<AlertaResponseDTO>> gerarAlertas(@PathVariable Long id) {
        return ResponseEntity.ok(petService.gerarAlertas(id));
    }

    @GetMapping("/{id}/recomendacoes")
    @Operation(summary = "Retorna recomendações preventivas por fase de vida",
               description = "Recomendações personalizadas com base na espécie e fase de vida (Filhote, Adulto, Idoso)")
    public ResponseEntity<RecomendacaoResponseDTO> gerarRecomendacoes(@PathVariable Long id) {
        return ResponseEntity.ok(petService.gerarRecomendacoes(id));
    }
}
