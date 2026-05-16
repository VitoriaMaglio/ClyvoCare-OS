package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.ExameRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.ExameResponseDTO;
import br.com.clyvocare.clyvocare_api.service.ExameService;
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
@RequestMapping("/exames")
@RequiredArgsConstructor
@Tag(name = "Exames", description = "Exames laboratoriais e de imagem dos pets")
public class ExameController {

    private final ExameService exameService;

    @PostMapping
    @Operation(summary = "Registra um novo exame")
    public ResponseEntity<ExameResponseDTO> criar(@RequestBody @Valid ExameRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exameService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista exames com paginação e filtros",
               description = "Filtros: idPet, tipo, idConsulta, semResultado (boolean)")
    public ResponseEntity<Page<ExameResponseDTO>> listar(
            @Parameter(description = "Filtro por ID do pet") @RequestParam(required = false) Long idPet,
            @Parameter(description = "Filtro por tipo", example = "Hemograma") @RequestParam(required = false) String tipo,
            @Parameter(description = "Filtro por ID da consulta") @RequestParam(required = false) Long idConsulta,
            @Parameter(description = "Apenas sem resultado") @RequestParam(required = false) Boolean semResultado,
            @PageableDefault(size = 10, sort = "dataSolicitacao", direction = Sort.Direction.DESC) Pageable pageable) {

        if (idPet != null) return ResponseEntity.ok(exameService.buscarPorPet(idPet, pageable));
        if (tipo != null && !tipo.isBlank()) return ResponseEntity.ok(exameService.buscarPorTipo(tipo, pageable));
        if (idConsulta != null) return ResponseEntity.ok(exameService.buscarPorConsulta(idConsulta, pageable));
        if (Boolean.TRUE.equals(semResultado)) return ResponseEntity.ok(exameService.buscarSemResultado(pageable));
        return ResponseEntity.ok(exameService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca exame por ID")
    public ResponseEntity<ExameResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(exameService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um exame", description = "Usado principalmente para informar resultado")
    public ResponseEntity<ExameResponseDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid ExameRequestDTO dto) {
        return ResponseEntity.ok(exameService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um exame")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        exameService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
