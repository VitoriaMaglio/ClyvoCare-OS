package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    Page<Cidade> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    List<Cidade> findByEstado(String estado);
    Page<Cidade> findByEstado(String estado, Pageable pageable);
    Page<Cidade> findByRegiao(String regiao, Pageable pageable);
}
