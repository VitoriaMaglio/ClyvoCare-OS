package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.HistoricoPeso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HistoricoPesoRepository extends JpaRepository<HistoricoPeso, Long> {

    // Histórico ordenado por data decrescente
    Page<HistoricoPeso> findByPetIdPetOrderByDataRegistroDesc(Long idPet, Pageable pageable);

    // Último peso registrado (para calcular variação)
    @Query("""
            SELECT h FROM HistoricoPeso h
            WHERE h.pet.idPet = :idPet
            ORDER BY h.dataRegistro DESC
            """)
    Optional<HistoricoPeso> findUltimoPeso(@Param("idPet") Long idPet);

    // Penúltimo peso (para calcular variação em relação ao anterior)
    @Query("""
            SELECT h FROM HistoricoPeso h
            WHERE h.pet.idPet = :idPet
              AND h.idHistorico != :idAtual
            ORDER BY h.dataRegistro DESC
            """)
    Page<HistoricoPeso> findPesoAnterior(
            @Param("idPet") Long idPet,
            @Param("idAtual") Long idAtual,
            Pageable pageable
    );

    long countByPetIdPet(Long idPet);
}
