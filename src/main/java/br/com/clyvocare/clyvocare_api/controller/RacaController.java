package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.RacaRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.RacaResponseDTO;
import br.com.clyvocare.clyvocare_api.service.RacaService;
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
@RequestMapping("/racas")
@RequiredArgsConstructor
@Tag(name = "Raças", description = "Catálogo de raças por espécie")
public class RacaController {

    private final RacaService racaService;

    @PostMapping
    @Operation(summary = "Cadastra uma nova raça")
    public ResponseEntity<RacaResponseDTO> criar(@RequestBody @Valid RacaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(racaService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista raças com paginação e filtros",
               description = "Filtros: idEspecie, porte (Pequeno/Médio/Grande)")
    public ResponseEntity<Page<RacaResponseDTO>> listar(
            @Parameter(description = "Filtro por ID da espécie") @RequestParam(required = false) Long idEspecie,
            @Parameter(description = "Filtro por porte", example = "Grande") @RequestParam(required = false) String porte,
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {

        if (idEspecie != null) return ResponseEntity.ok(racaService.buscarPorEspecie(idEspecie, pageable));
        if (porte != null && !porte.isBlank()) return ResponseEntity.ok(racaService.buscarPorPorte(porte, pageable));
        return ResponseEntity.ok(racaService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca raça por ID")
    public ResponseEntity<RacaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(racaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma raça")
    public ResponseEntity<RacaResponseDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid RacaRequestDTO dto) {
        return ResponseEntity.ok(racaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma raça")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        racaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
