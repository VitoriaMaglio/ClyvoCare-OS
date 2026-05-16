package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.CidadeResponseDTO;
import br.com.clyvocare.clyvocare_api.service.CidadeService;
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
@RequestMapping("/cidades")
@RequiredArgsConstructor
@Tag(name = "Cidades", description = "Municípios brasileiros — apenas consulta")
public class CidadeController {

    private final CidadeService cidadeService;

    @GetMapping
    @Operation(summary = "Lista cidades com paginação e filtros",
               description = "Filtros: nome, estado (UF), regiao")
    public ResponseEntity<Page<CidadeResponseDTO>> listar(
            @Parameter(description = "Filtro por nome", example = "São Paulo") @RequestParam(required = false) String nome,
            @Parameter(description = "Filtro por estado UF", example = "SP") @RequestParam(required = false) String estado,
            @Parameter(description = "Filtro por região", example = "Sudeste") @RequestParam(required = false) String regiao,
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {

        if (nome != null && !nome.isBlank()) return ResponseEntity.ok(cidadeService.buscarPorNome(nome, pageable));
        if (estado != null && !estado.isBlank()) return ResponseEntity.ok(cidadeService.buscarPorEstado(estado, pageable));
        if (regiao != null && !regiao.isBlank()) return ResponseEntity.ok(cidadeService.buscarPorRegiao(regiao, pageable));
        return ResponseEntity.ok(cidadeService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca cidade por ID")
    public ResponseEntity<CidadeResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cidadeService.buscarPorId(id));
    }
}
