package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.LembreteRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.LembreteResponseDTO;
import br.com.clyvocare.clyvocare_api.service.LembreteService;
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
@RequestMapping("/lembretes")
@RequiredArgsConstructor
@Tag(name = "Lembretes", description = "Lembretes automáticos para tutores via WhatsApp, Push ou E-mail")
public class LembreteController {

    private final LembreteService lembreteService;

    @PostMapping
    @Operation(summary = "Cria um novo lembrete")
    public ResponseEntity<LembreteResponseDTO> criar(@RequestBody @Valid LembreteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lembreteService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista lembretes com paginação e filtros",
               description = "Filtros: idPet, idTutor, pendentes (boolean)")
    public ResponseEntity<Page<LembreteResponseDTO>> listar(
            @Parameter(description = "Filtro por ID do pet") @RequestParam(required = false) Long idPet,
            @Parameter(description = "Filtro por ID do tutor") @RequestParam(required = false) Long idTutor,
            @Parameter(description = "Listar apenas pendentes a enviar") @RequestParam(required = false) Boolean pendentes,
            @PageableDefault(size = 10, sort = "dataEvento", direction = Sort.Direction.ASC) Pageable pageable) {

        if (Boolean.TRUE.equals(pendentes)) {
            return ResponseEntity.ok(lembreteService.buscarPendentes(pageable));
        }
        if (idPet != null) {
            return ResponseEntity.ok(lembreteService.buscarPorPet(idPet, pageable));
        }
        if (idTutor != null) {
            return ResponseEntity.ok(lembreteService.buscarPorTutor(idTutor, pageable));
        }
        return ResponseEntity.ok(lembreteService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca lembrete por ID")
    public ResponseEntity<LembreteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(lembreteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um lembrete")
    public ResponseEntity<LembreteResponseDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid LembreteRequestDTO dto) {
        return ResponseEntity.ok(lembreteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um lembrete")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        lembreteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
