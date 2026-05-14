package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Vacinacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long> {

    // Histórico de vacinações do pet
    Page<Vacinacao> findByPetIdPetOrderByDataAplicacaoDesc(Long idPet, Pageable pageable);

    // Vacinas atrasadas do pet: proxima_dose < hoje
    // Usado no score de risco e alertas
    @Query("""
            SELECT COUNT(v) FROM Vacinacao v
            WHERE v.pet.idPet = :idPet
              AND v.proximaDose IS NOT NULL
              AND v.proximaDose < CURRENT_DATE
            """)
    int countVacinasAtrasadas(@Param("idPet") Long idPet);

    // Lista vacinações atrasadas do pet (para exibir detalhes no alerta)
    @Query("""
            SELECT v FROM Vacinacao v
            WHERE v.pet.idPet = :idPet
              AND v.proximaDose IS NOT NULL
              AND v.proximaDose < CURRENT_DATE
            ORDER BY v.proximaDose ASC
            """)
    Page<Vacinacao> findVacinasAtrasadasByPet(@Param("idPet") Long idPet, Pageable pageable);

    // Total de vacinas atrasadas no sistema (dashboard)
    @Query("""
            SELECT COUNT(v) FROM Vacinacao v
            WHERE v.proximaDose IS NOT NULL
              AND v.proximaDose < CURRENT_DATE
            """)
    long countTotalVacinasAtrasadas();

    // Contagem por pet
    long countByPetIdPet(Long idPet);
}
