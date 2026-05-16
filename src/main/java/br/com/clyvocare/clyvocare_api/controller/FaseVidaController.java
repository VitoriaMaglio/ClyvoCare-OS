package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.FaseVidaResponseDTO;
import br.com.clyvocare.clyvocare_api.service.FaseVidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fases-vida")
@RequiredArgsConstructor
@Tag(name = "Fases de Vida", description = "Fases etárias por espécie (Filhote, Adulto, Idoso)")
public class FaseVidaController {

    private final FaseVidaService faseVidaService;

    @GetMapping
    @Operation(summary = "Lista fases de vida com paginação",
               description = "Filtro opcional: idEspecie")
    public ResponseEntity<Page<FaseVidaResponseDTO>> listar(
            @Parameter(description = "Filtro por ID da espécie") @RequestParam(required = false) Long idEspecie,
            @PageableDefault(size = 10, sort = "idadeMinMeses", direction = Sort.Direction.ASC) Pageable pageable) {

        if (idEspecie != null) return ResponseEntity.ok(faseVidaService.buscarPorEspecie(idEspecie, pageable));
        return ResponseEntity.ok(faseVidaService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca fase de vida por ID")
    public ResponseEntity<FaseVidaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(faseVidaService.buscarPorId(id));
    }
}
