package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.ProtocoloVacinacaoRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.ProtocoloVacinacaoResponseDTO;
import br.com.clyvocare.clyvocare_api.service.ProtocoloVacinacaoService;
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
@RequestMapping("/protocolos-vacinacao")
@RequiredArgsConstructor
@Tag(name = "Protocolos de Vacinação", description = "Define quais vacinas aplicar em cada fase de vida")
public class ProtocoloVacinacaoController {

    private final ProtocoloVacinacaoService protocoloService;

    @PostMapping
    @Operation(summary = "Cria um novo protocolo de vacinação")
    public ResponseEntity<ProtocoloVacinacaoResponseDTO> criar(@RequestBody @Valid ProtocoloVacinacaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(protocoloService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista protocolos com paginação e filtros",
               description = "Filtros: idFase, idEspecie")
    public ResponseEntity<Page<ProtocoloVacinacaoResponseDTO>> listar(
            @Parameter(description = "Filtro por ID da fase de vida") @RequestParam(required = false) Long idFase,
            @Parameter(description = "Filtro por ID da espécie") @RequestParam(required = false) Long idEspecie,
            @PageableDefault(size = 10, sort = "idProtocolo", direction = Sort.Direction.ASC) Pageable pageable) {

        if (idFase != null) return ResponseEntity.ok(protocoloService.buscarPorFase(idFase, pageable));
        if (idEspecie != null) return ResponseEntity.ok(protocoloService.buscarPorEspecie(idEspecie, pageable));
        return ResponseEntity.ok(protocoloService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca protocolo por ID")
    public ResponseEntity<ProtocoloVacinacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(protocoloService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um protocolo de vacinação")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        protocoloService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
