package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.PrescricaoRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.PrescricaoResponseDTO;
import br.com.clyvocare.clyvocare_api.service.PrescricaoService;
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
@RequestMapping("/prescricoes")
@RequiredArgsConstructor
@Tag(name = "Prescrições", description = "Medicamentos prescritos por tratamento")
public class PrescricaoController {

    private final PrescricaoService prescricaoService;

    @PostMapping
    @Operation(summary = "Registra uma nova prescrição")
    public ResponseEntity<PrescricaoResponseDTO> criar(@RequestBody @Valid PrescricaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prescricaoService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista prescrições com paginação e filtros",
               description = "Filtros: idTratamento, idPet")
    public ResponseEntity<Page<PrescricaoResponseDTO>> listar(
            @Parameter(description = "Filtro por ID do tratamento") @RequestParam(required = false) Long idTratamento,
            @Parameter(description = "Filtro por ID do pet") @RequestParam(required = false) Long idPet,
            @PageableDefault(size = 10, sort = "idPrescricao", direction = Sort.Direction.DESC) Pageable pageable) {

        if (idTratamento != null) {
            return ResponseEntity.ok(prescricaoService.buscarPorTratamento(idTratamento, pageable));
        }
        if (idPet != null) {
            return ResponseEntity.ok(prescricaoService.buscarPorPet(idPet, pageable));
        }
        return ResponseEntity.ok(prescricaoService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca prescrição por ID")
    public ResponseEntity<PrescricaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prescricaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma prescrição")
    public ResponseEntity<PrescricaoResponseDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid PrescricaoRequestDTO dto) {
        return ResponseEntity.ok(prescricaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma prescrição")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        prescricaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
