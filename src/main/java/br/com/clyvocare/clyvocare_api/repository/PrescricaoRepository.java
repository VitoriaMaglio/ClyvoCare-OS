package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Prescricao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrescricaoRepository extends JpaRepository<Prescricao, Long> {

    // Prescrições de um tratamento
    Page<Prescricao> findByTratamentoIdTratamento(Long idTratamento, Pageable pageable);

    // Prescrições de um medicamento
    Page<Prescricao> findByMedicamentoIdMedicamento(Long idMedicamento, Pageable pageable);

    // Todas as prescrições de um pet (via tratamento)
    @Query("""
            SELECT p FROM Prescricao p
            WHERE p.tratamento.pet.idPet = :idPet
            ORDER BY p.tratamento.dataInicio DESC
            """)
    Page<Prescricao> findByPet(@Param("idPet") Long idPet, Pageable pageable);

    boolean existsByTratamentoIdTratamento(Long idTratamento);
}
