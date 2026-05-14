package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // Usado pelo PetService para checar dependências antes de deletar pet
    boolean existsByPetIdPet(Long idPet);

    // Listagens por pet
    Page<Consulta> findByPetIdPetOrderByDataConsultaDesc(Long idPet, Pageable pageable);

    // Listagens por status
    Page<Consulta> findByStatus(String status, Pageable pageable);

    // Retornos agendados (consultas com status AGENDADA)
    @Query("SELECT c FROM Consulta c WHERE c.status = 'AGENDADA' AND c.pet.idPet = :idPet")
    Page<Consulta> findRetornosPendentesByPet(@Param("idPet") Long idPet, Pageable pageable);

    // Busca por clínica
    Page<Consulta> findByClinicaIdClinica(Long idClinica, Pageable pageable);

    // Busca por veterinário
    Page<Consulta> findByVeterinarioIdVeterinario(Long idVeterinario, Pageable pageable);

    // Última consulta realizada do pet
    @Query("""
            SELECT c FROM Consulta c
            WHERE c.pet.idPet = :idPet
              AND c.status = 'REALIZADA'
            ORDER BY c.dataConsulta DESC
            """)
    Optional<Consulta> findUltimaConsultaRealizada(@Param("idPet") Long idPet);

    // Meses desde a última consulta realizada (usado no score de risco)
    @Query("""
            SELECT COALESCE(
                MONTHS_BETWEEN(CURRENT_DATE, MAX(c.dataConsulta)), 999
            )
            FROM Consulta c
            WHERE c.pet.idPet = :idPet
              AND c.status = 'REALIZADA'
            """)
    int mesesDesdeUltimaConsulta(@Param("idPet") Long idPet);

    // Contagem total por pet (dashboard)
    long countByPetIdPet(Long idPet);

    // Contagem por status (dashboard)
    long countByStatus(String status);
}
