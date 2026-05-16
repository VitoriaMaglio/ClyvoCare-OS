package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    Page<Medicamento> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<Medicamento> findByPrincipioAtivoContainingIgnoreCase(String principioAtivo, Pageable pageable);

    boolean existsByNomeIgnoreCase(String nome);
    boolean existsByNomeIgnoreCaseAndIdMedicamentoNot(String nome, Long id);
}
