package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Tutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    // Verifica duplicidade antes de salvar
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByEmailAndIdTutorNot(String email, Long idTutor);
    boolean existsByCpfAndIdTutorNot(String cpf, Long idTutor);

    // Busca por e-mail (login)
    Optional<Tutor> findByEmail(String email);

    // Busca com parâmetro — nome contendo (busca parcial, case-insensitive)
    Page<Tutor> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    // Busca por estado via cidade (JPQL)
    @Query("SELECT t FROM Tutor t WHERE t.cidade.estado = :estado")
    Page<Tutor> findByEstado(@Param("estado") String estado, Pageable pageable);

    // Busca por cidade
    @Query("SELECT t FROM Tutor t WHERE t.cidade.idCidade = :idCidade")
    Page<Tutor> findByCidadeId(@Param("idCidade") Long idCidade, Pageable pageable);
}
