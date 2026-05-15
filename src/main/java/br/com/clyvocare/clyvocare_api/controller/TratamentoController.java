package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.TratamentoRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.TratamentoResponseDTO;
import br.com.clyvocare.clyvocare_api.service.TratamentoService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tratamentos")
@RequiredArgsConstructor
@Tag(name = "Tratamentos", description = "Gerenciamento de tratamentos veterinários")
public class TratamentoController {

    private final TratamentoService tratamentoService;

    @PostMapping
    @Operation(summary = "Registra um novo tratamento")
    public ResponseEntity<TratamentoResponseDTO> criar(@RequestBody @Valid TratamentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tratamentoService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista tratamentos com paginação e filtros",
               description = "Filtros: idPet, status (ATIVO/CONCLUIDO/SUSPENSO), ativos (boolean)")
    public ResponseEntity<Page<TratamentoResponseDTO>> listar(
            @Parameter(description = "Filtro por ID do pet") @RequestParam(required = false) Long idPet,
            @Parameter(description = "Filtro por status", example = "ATIVO") @RequestParam(required = false) String status,
            @PageableDefault(size = 10, sort = "dataInicio", direction = Sort.Direction.DESC) Pageable pageable) {

        if (idPet != null && status != null) {
            return ResponseEntity.ok(tratamentoService.buscarAtivos(pageable));
        }
        if (idPet != null) return ResponseEntity.ok(tratamentoService.buscarPorPet(idPet, pageable));
        if (status != null && !status.isBlank()) return ResponseEntity.ok(tratamentoService.buscarAtivos(pageable));
        return ResponseEntity.ok(tratamentoService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca tratamento por ID")
    public ResponseEntity<TratamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tratamentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um tratamento")
    public ResponseEntity<TratamentoResponseDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid TratamentoRequestDTO dto) {
        return ResponseEntity.ok(tratamentoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um tratamento", description = "Não permite remover tratamentos ATIVOS")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tratamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
