package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.VacinacaoRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.VacinacaoResponseDTO;
import br.com.clyvocare.clyvocare_api.service.VacinacaoService;
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
@RequestMapping("/vacinacoes")
@RequiredArgsConstructor
@Tag(name = "Vacinações", description = "Histórico de vacinações dos pets")
public class VacinacaoController {

    private final VacinacaoService vacinacaoService;

    @PostMapping
    @Operation(summary = "Registra uma vacinação aplicada")
    public ResponseEntity<VacinacaoResponseDTO> criar(@RequestBody @Valid VacinacaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacinacaoService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista vacinações com paginação e filtros",
               description = "Filtros: idPet, atrasadas (boolean)")
    public ResponseEntity<Page<VacinacaoResponseDTO>> listar(
            @Parameter(description = "Filtro por ID do pet") @RequestParam(required = false) Long idPet,
            @Parameter(description = "Listar apenas atrasadas") @RequestParam(required = false) Boolean atrasadas,
            @PageableDefault(size = 10, sort = "dataAplicacao", direction = Sort.Direction.DESC) Pageable pageable) {

        if (idPet != null && Boolean.TRUE.equals(atrasadas)) {
            return ResponseEntity.ok(vacinacaoService.buscarAtrasadasPorPet(idPet, pageable));
        }
        if (idPet != null) return ResponseEntity.ok(vacinacaoService.buscarPorPet(idPet, pageable));
        return ResponseEntity.ok(vacinacaoService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca vacinação por ID")
    public ResponseEntity<VacinacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vacinacaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza dados de uma vacinação")
    public ResponseEntity<VacinacaoResponseDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid VacinacaoRequestDTO dto) {
        return ResponseEntity.ok(vacinacaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um registro de vacinação")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vacinacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
