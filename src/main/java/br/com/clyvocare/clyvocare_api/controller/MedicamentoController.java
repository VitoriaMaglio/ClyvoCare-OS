package br.com.clyvocare.clyvocare_api.controller;

import br.com.clyvocare.clyvocare_api.dto.MedicamentoRequestDTO;
import br.com.clyvocare.clyvocare_api.dto.MedicamentoResponseDTO;
import br.com.clyvocare.clyvocare_api.service.MedicamentoService;
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
@RequestMapping("/medicamentos")
@RequiredArgsConstructor
@Tag(name = "Medicamentos", description = "Catálogo de medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @PostMapping
    @Operation(summary = "Cadastra um novo medicamento")
    public ResponseEntity<MedicamentoResponseDTO> criar(@RequestBody @Valid MedicamentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista medicamentos com paginação e filtros",
               description = "Filtros: nome, principioAtivo")
    public ResponseEntity<Page<MedicamentoResponseDTO>> listar(
            @Parameter(description = "Filtro por nome") @RequestParam(required = false) String nome,
            @Parameter(description = "Filtro por princípio ativo") @RequestParam(required = false) String principioAtivo,
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {

        if (nome != null && !nome.isBlank()) {
            return ResponseEntity.ok(medicamentoService.buscarPorNome(nome, pageable));
        }
        if (principioAtivo != null && !principioAtivo.isBlank()) {
            return ResponseEntity.ok(medicamentoService.buscarPorPrincipioAtivo(principioAtivo, pageable));
        }
        return ResponseEntity.ok(medicamentoService.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca medicamento por ID")
    public ResponseEntity<MedicamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um medicamento")
    public ResponseEntity<MedicamentoResponseDTO> atualizar(
            @PathVariable Long id, @RequestBody @Valid MedicamentoRequestDTO dto) {
        return ResponseEntity.ok(medicamentoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um medicamento")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
