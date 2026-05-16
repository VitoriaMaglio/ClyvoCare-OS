package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.ProtocoloVacinacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProtocoloVacinacaoRepository extends JpaRepository<ProtocoloVacinacao, Long> {

    Page<ProtocoloVacinacao> findByFaseVidaIdFase(Long idFase, Pageable pageable);

    Page<ProtocoloVacinacao> findByVacinaIdVacina(Long idVacina, Pageable pageable);

    // Protocolos obrigatórios de uma fase
    @Query("""
            SELECT p FROM ProtocoloVacinacao p
            WHERE p.faseVida.idFase = :idFase
              AND p.obrigatoria = 'S'
            """)
    List<ProtocoloVacinacao> findObrigatoriosByFase(@Param("idFase") Long idFase);

    // Protocolos por espécie (via fase de vida)
    @Query("""
            SELECT p FROM ProtocoloVacinacao p
            WHERE p.faseVida.especie.idEspecie = :idEspecie
            """)
    Page<ProtocoloVacinacao> findByEspecie(@Param("idEspecie") Long idEspecie, Pageable pageable);
}
