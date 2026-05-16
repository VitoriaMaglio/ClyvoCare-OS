package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.AlertaRegionalResponseDTO;
import br.com.clyvocare.clyvocare_api.service.AlertaRegionalService;
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
@RequestMapping("/alertas-regionais")
@RequiredArgsConstructor
@Tag(name = "Alertas Regionais", description = "Alertas epidemiológicos por cidade gerados pelo módulo de IA")
public class AlertaRegionalController {

    private final AlertaRegionalService alertaService;

    @GetMapping
    @Operation(summary = "Lista alertas regionais com filtros",
               description = "Filtros: ativos (boolean), estado (UF), idCidade, nivel (BAIXO/MEDIO/ALTO)")
    public ResponseEntity<Page<AlertaRegionalResponseDTO>> listar(
            @Parameter(description = "Apenas alertas ativos") @RequestParam(required = false) Boolean ativos,
            @Parameter(description = "Filtro por estado UF", example = "SP") @RequestParam(required = false) String estado,
            @Parameter(description = "Filtro por ID da cidade") @RequestParam(required = false) Long idCidade,
            @Parameter(description = "Filtro por nível de risco", example = "ALTO") @RequestParam(required = false) String nivel,
            @PageableDefault(size = 10, sort = "dataInicio", direction = Sort.Direction.DESC) Pageable pageable) {

        if (Boolean.TRUE.equals(ativos) && estado != null) return ResponseEntity.ok(alertaService.buscarPorEstado(estado, pageable));
        if (Boolean.TRUE.equals(ativos)) return ResponseEntity.ok(alertaService.buscarAtivos(pageable));
        if (estado != null && !estado.isBlank()) return ResponseEntity.ok(alertaService.buscarPorEstado(estado, pageable));
        if (idCidade != null) return ResponseEntity.ok(alertaService.buscarPorCidade(idCidade, pageable));
        if (nivel != null && !nivel.isBlank()) return ResponseEntity.ok(alertaService.buscarPorNivel(nivel, pageable));
        return ResponseEntity.ok(alertaService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca alerta regional por ID")
    public ResponseEntity<AlertaRegionalResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alertaService.buscarPorId(id));
    }
}
