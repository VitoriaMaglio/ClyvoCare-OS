package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Lembrete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LembreteRepository extends JpaRepository<Lembrete, Long> {

    Page<Lembrete> findByPetIdPet(Long idPet, Pageable pageable);

    Page<Lembrete> findByTutorIdTutor(Long idTutor, Pageable pageable);

    Page<Lembrete> findByStatus(String status, Pageable pageable);

    Page<Lembrete> findByCanal(String canal, Pageable pageable);

    // Lembretes pendentes e com data futura (a enviar)
    @Query("""
            SELECT l FROM Lembrete l
            WHERE l.status = 'PENDENTE'
              AND l.dataEvento >= CURRENT_DATE
            ORDER BY l.dataEvento ASC
            """)
    Page<Lembrete> findPendentesAEnviar(Pageable pageable);

    // Lembretes vencidos (data passou mas ainda PENDENTE)
    @Query("""
            SELECT l FROM Lembrete l
            WHERE l.status = 'PENDENTE'
              AND l.dataEvento < CURRENT_DATE
            """)
    Page<Lembrete> findVencidos(Pageable pageable);

    long countByStatus(String status);
}
