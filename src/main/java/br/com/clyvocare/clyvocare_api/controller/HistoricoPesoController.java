package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.HistoricoPesoRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.HistoricoPesoResponseDTO;
import br.com.clyvocare.clyvocare_api.service.HistoricoPesoService;
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
@RequestMapping("/historico-peso")
@RequiredArgsConstructor
@Tag(name = "Histórico de Peso", description = "Acompanhamento nutricional e evolução de peso dos pets")
public class HistoricoPesoController {

    private final HistoricoPesoService historicoPesoService;

    @PostMapping
    @Operation(summary = "Registra um novo peso para o pet",
               description = "Além de registrar no histórico, atualiza automaticamente o peso atual do pet")
    public ResponseEntity<HistoricoPesoResponseDTO> registrar(@RequestBody @Valid HistoricoPesoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historicoPesoService.registrar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista histórico de peso com paginação",
               description = "Filtro obrigatório: idPet")
    public ResponseEntity<Page<HistoricoPesoResponseDTO>> listar(
            @Parameter(description = "ID do pet", required = true) @RequestParam Long idPet,
            @PageableDefault(size = 10, sort = "dataRegistro", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(historicoPesoService.buscarPorPet(idPet, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca registro de peso por ID")
    public ResponseEntity<HistoricoPesoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(historicoPesoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um registro de peso")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        historicoPesoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
