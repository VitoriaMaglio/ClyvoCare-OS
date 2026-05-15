package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.FaseVida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FaseVidaRepository extends JpaRepository<FaseVida, Long> {

    List<FaseVida> findByEspecieIdEspecie(Long idEspecie);

    // Busca a fase atual do pet pela idade em meses e espécie
    @Query("""
            SELECT f FROM FaseVida f
            WHERE f.especie.idEspecie = :idEspecie
              AND f.idadeMinMeses <= :idadeMeses
              AND (f.idadeMaxMeses IS NULL OR f.idadeMaxMeses >= :idadeMeses)
            """)
    Optional<FaseVida> findFaseAtual(
            @Param("idEspecie") Long idEspecie,
            @Param("idadeMeses") int idadeMeses
    );
}
