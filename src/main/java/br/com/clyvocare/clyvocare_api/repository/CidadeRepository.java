package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    List<Cidade> findByEstado(String estado);
    List<Cidade> findByNomeContainingIgnoreCase(String nome);
}
