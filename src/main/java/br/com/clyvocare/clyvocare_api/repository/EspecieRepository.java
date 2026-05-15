package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Especie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecieRepository extends JpaRepository<Especie, Long> {

    Optional<Especie> findByNomeIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);
    Page<Especie> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
