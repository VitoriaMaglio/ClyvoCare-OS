package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Clinica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClinicaRepository extends JpaRepository<Clinica, Long> {

    boolean existsByCnpj(String cnpj);
    boolean existsByCnpjAndIdClinicaNot(String cnpj, Long idClinica);

    Page<Clinica> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<Clinica> findByPlanoAssinatura(String plano, Pageable pageable);

    @Query("SELECT c FROM Clinica c WHERE c.cidade.estado = :estado")
    Page<Clinica> findByEstado(@Param("estado") String estado, Pageable pageable);
}
