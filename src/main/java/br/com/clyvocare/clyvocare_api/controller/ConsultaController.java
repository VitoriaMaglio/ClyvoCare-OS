package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.ConsultaRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.ConsultaResponseDTO;
import br.com.clyvocare.clyvocare_api.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
@Tag(name = "Consultas", description = "Gerenciamento de consultas veterinárias")
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    @Operation(summary = "Registra uma nova consulta")
    public ResponseEntity<ConsultaResponseDTO> criar(@RequestBody @Valid ConsultaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.criar(dto));
    }
    @Transactional(readOnly = true)
    @GetMapping
    @Operation(summary = "Lista consultas com paginação e filtros",
               description = "Filtros: idPet, status (AGENDADA/REALIZADA/CANCELADA), idClinica")
    public ResponseEntity<Page<ConsultaResponseDTO>> listar(
            @Parameter(description = "Filtro por ID do pet") @RequestParam(required = false) Long idPet,
            @Parameter(description = "Filtro por status", example = "REALIZADA") @RequestParam(required = false) String status,
            @Parameter(description = "Filtro por ID da clínica") @RequestParam(required = false) Long idClinica,
            @PageableDefault(size = 10, sort = "dataConsulta", direction = Sort.Direction.DESC) Pageable pageable) {

        if (idPet != null) return ResponseEntity.ok(consultaService.buscarPorPet(idPet, pageable));
        if (status != null && !status.isBlank()) return ResponseEntity.ok(consultaService.buscarPorStatus(status, pageable));
        if (idClinica != null) return ResponseEntity.ok(consultaService.buscarPorClinica(idClinica, pageable));
        return ResponseEntity.ok(consultaService.listar(pageable));
    }
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    @Operation(summary = "Busca consulta por ID")
    public ResponseEntity<ConsultaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma consulta")
    public ResponseEntity<ConsultaResponseDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid ConsultaRequestDTO dto) {
        return ResponseEntity.ok(consultaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma consulta", description = "Só permite remover consultas AGENDADAS ou CANCELADAS")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        consultaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
