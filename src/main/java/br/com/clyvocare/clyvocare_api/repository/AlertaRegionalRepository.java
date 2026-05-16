package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.AlertaRegional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlertaRegionalRepository extends JpaRepository<AlertaRegional, Long> {

    Page<AlertaRegional> findByCidadeIdCidade(Long idCidade, Pageable pageable);

    Page<AlertaRegional> findByNivelRisco(String nivelRisco, Pageable pageable);

    Page<AlertaRegional> findByCidadeEstado(String estado, Pageable pageable);

    // Alertas ainda ativos (data_fim nula ou futura)
    @Query("""
            SELECT a FROM AlertaRegional a
            WHERE a.dataFim IS NULL OR a.dataFim >= CURRENT_DATE
            ORDER BY a.nivelRisco DESC, a.dataInicio DESC
            """)
    Page<AlertaRegional> findAtivos(Pageable pageable);

    // Alertas ativos por estado
    @Query("""
            SELECT a FROM AlertaRegional a
            WHERE (a.dataFim IS NULL OR a.dataFim >= CURRENT_DATE)
              AND a.cidade.estado = :estado
            """)
    Page<AlertaRegional> findAtivosByEstado(@Param("estado") String estado, Pageable pageable);

    // Alertas ativos por cidade
    @Query("""
            SELECT a FROM AlertaRegional a
            WHERE (a.dataFim IS NULL OR a.dataFim >= CURRENT_DATE)
              AND a.cidade.idCidade = :idCidade
            """)
    Page<AlertaRegional> findAtivosByCidade(@Param("idCidade") Long idCidade, Pageable pageable);
}
