package br.com.clyvocare.clyvocare_api.repository;


import br.com.clyvocare.clyvocare_api.entity.Tratamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {

    // Usado pelo PetService para checar dependências antes de deletar pet
    boolean existsByPetIdPet(Long idPet);

    // Listagens por pet
    Page<Tratamento> findByPetIdPetOrderByDataInicioDesc(Long idPet, Pageable pageable);

    // Tratamentos ativos
    Page<Tratamento> findByStatus(String status, Pageable pageable);

    // Tratamentos ativos de um pet
    Page<Tratamento> findByPetIdPetAndStatus(Long idPet, String status, Pageable pageable);

    // Tratamentos abandonados: ATIVO há mais de 30 dias sem data_fim
    // Usado no score de risco
    @Query("""
            SELECT COUNT(t) FROM Tratamento t
            WHERE t.pet.idPet = :idPet
              AND t.status = 'ATIVO'
              AND t.dataInicio <= FUNCTION('ADD_MONTHS', CURRENT_DATE, -1)
            """)
    int countTratamentosAbandonados(@Param("idPet") Long idPet);

    // Contagem total de tratamentos ativos (dashboard)
    long countByStatus(String status);

    // Contagem por pet
    long countByPetIdPet(Long idPet);
}
