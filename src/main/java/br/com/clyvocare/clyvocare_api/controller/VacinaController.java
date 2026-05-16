package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.VacinaRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.VacinaResponseDTO;
import br.com.clyvocare.clyvocare_api.service.VacinaService;
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
@RequestMapping("/vacinas")
@RequiredArgsConstructor
@Tag(name = "Vacinas", description = "Catálogo de vacinas por espécie")
public class VacinaController {

    private final VacinaService vacinaService;

    @PostMapping
    @Operation(summary = "Cadastra uma nova vacina")
    public ResponseEntity<VacinaResponseDTO> criar(@RequestBody @Valid VacinaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacinaService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista vacinas com paginação e filtros",
               description = "Filtros: nome, idEspecie")
    public ResponseEntity<Page<VacinaResponseDTO>> listar(
            @Parameter(description = "Filtro por nome") @RequestParam(required = false) String nome,
            @Parameter(description = "Filtro por ID da espécie") @RequestParam(required = false) Long idEspecie,
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {

        if (nome != null && !nome.isBlank()) return ResponseEntity.ok(vacinaService.buscarPorNome(nome, pageable));
        if (idEspecie != null) return ResponseEntity.ok(vacinaService.buscarPorEspecie(idEspecie, pageable));
        return ResponseEntity.ok(vacinaService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca vacina por ID")
    public ResponseEntity<VacinaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vacinaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma vacina")
    public ResponseEntity<VacinaResponseDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid VacinaRequestDTO dto) {
        return ResponseEntity.ok(vacinaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma vacina")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vacinaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
