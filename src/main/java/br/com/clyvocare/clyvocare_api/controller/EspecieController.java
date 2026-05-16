package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.EspecieRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.EspecieResponseDTO;
import br.com.clyvocare.clyvocare_api.service.EspecieService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/especies")
@RequiredArgsConstructor
@Tag(name = "Espécies", description = "Catálogo de espécies animais")
public class EspecieController {

    private final EspecieService especieService;

    @PostMapping
    @Operation(summary = "Cadastra uma nova espécie")
    public ResponseEntity<EspecieResponseDTO> criar(@RequestBody @Valid EspecieRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(especieService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista espécies com paginação")
    public ResponseEntity<Page<EspecieResponseDTO>> listar(
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(especieService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca espécie por ID")
    public ResponseEntity<EspecieResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(especieService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma espécie")
    public ResponseEntity<EspecieResponseDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid EspecieRequestDTO dto) {
        return ResponseEntity.ok(especieService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma espécie")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        especieService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
