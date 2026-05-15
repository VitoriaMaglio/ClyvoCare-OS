package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Raca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RacaRepository extends JpaRepository<Raca, Long> {

    Page<Raca> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    List<Raca> findByEspecieIdEspecie(Long idEspecie);
    Page<Raca> findByEspecieIdEspecie(Long idEspecie, Pageable pageable);
    Page<Raca> findByPorteIgnoreCase(String porte, Pageable pageable);
}
