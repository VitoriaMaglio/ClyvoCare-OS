package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.ClinicaRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.ClinicaResponseDTO;
import br.com.clyvocare.clyvocare_api.service.ClinicaService;
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
@RequestMapping("/clinicas")
@RequiredArgsConstructor
@Tag(name = "Clínicas", description = "Gerenciamento de clínicas veterinárias")
public class ClinicaController {

    private final ClinicaService clinicaService;

    @PostMapping
    @Operation(summary = "Cadastra uma nova clínica")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Clínica criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "422", description = "CNPJ já cadastrado")
    })
    public ResponseEntity<ClinicaResponseDTO> criar(@RequestBody @Valid ClinicaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clinicaService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista clínicas com paginação, ordenação e filtros",
               description = "Filtros: nome, plano (BASICO/PRO/ENTERPRISE), estado (UF)")
    public ResponseEntity<Page<ClinicaResponseDTO>> listar(
            @Parameter(description = "Filtro por nome") @RequestParam(required = false) String nome,
            @Parameter(description = "Filtro por plano", example = "PRO") @RequestParam(required = false) String plano,
            @Parameter(description = "Filtro por estado UF", example = "SP") @RequestParam(required = false) String estado,
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {

        if (nome != null && !nome.isBlank()) {
            return ResponseEntity.ok(clinicaService.buscarPorNome(nome, pageable));
        }
        if (plano != null && !plano.isBlank()) {
            return ResponseEntity.ok(clinicaService.buscarPorPlano(plano, pageable));
        }
        if (estado != null && !estado.isBlank()) {
            return ResponseEntity.ok(clinicaService.buscarPorEstado(estado, pageable));
        }
        return ResponseEntity.ok(clinicaService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca clínica por ID")
    public ResponseEntity<ClinicaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clinicaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza dados da clínica")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Clínica atualizada"),
        @ApiResponse(responseCode = "404", description = "Clínica não encontrada"),
        @ApiResponse(responseCode = "422", description = "CNPJ já em uso")
    })
    public ResponseEntity<ClinicaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ClinicaRequestDTO dto) {
        return ResponseEntity.ok(clinicaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma clínica",
               description = "Não permite exclusão se houver veterinários vinculados")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Clínica removida"),
        @ApiResponse(responseCode = "404", description = "Clínica não encontrada"),
        @ApiResponse(responseCode = "422", description = "Clínica possui veterinários vinculados")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clinicaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
