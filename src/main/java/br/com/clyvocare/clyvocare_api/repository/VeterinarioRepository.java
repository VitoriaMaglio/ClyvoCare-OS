package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Veterinario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    boolean existsByCrmv(String crmv);
    boolean existsByCrmvAndIdVeterinarioNot(String crmv, Long idVeterinario);

    Page<Veterinario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<Veterinario> findByEspecialidadeContainingIgnoreCase(String especialidade, Pageable pageable);

    @Query("SELECT v FROM Veterinario v WHERE v.clinica.idClinica = :idClinica")
    Page<Veterinario> findByClinica(@Param("idClinica") Long idClinica, Pageable pageable);
}
