package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Vacina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {

    Page<Vacina> findByEspecieIdEspecie(Long idEspecie, Pageable pageable);
    Page<Vacina> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
