package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    // Busca por tutor
    Page<Pet> findByTutorIdTutor(Long idTutor, Pageable pageable);

    // Busca por espécie (nome)
    @Query("SELECT p FROM Pet p WHERE LOWER(p.especie.nome) = LOWER(:nomeEspecie)")
    Page<Pet> findByEspecie(@Param("nomeEspecie") String nomeEspecie, Pageable pageable);

    // Busca por nome parcial
    Page<Pet> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    // Busca por microchip
    Optional<Pet> findByMicrochip(String microchip);
    boolean existsByMicrochip(String microchip);
    boolean existsByMicrochipAndIdPetNot(String microchip, Long idPet);

    // Conta pets por tutor (usado no dashboard)
    long countByTutorIdTutor(Long idTutor);

    // Busca pets com vacinas atrasadas (para score de risco e dashboard)
    @Query("""
            SELECT DISTINCT p FROM Pet p
            JOIN Vacinacao v ON v.pet.idPet = p.idPet
            WHERE v.proximaDose < CURRENT_DATE
            """)
    Page<Pet> findPetsComVacinasAtrasadas(Pageable pageable);

    // Busca pets sem consulta há mais de X meses (para alertas)
    @Query("""
            SELECT p FROM Pet p
            WHERE p.idPet NOT IN (
                SELECT c.pet.idPet FROM Consulta c
                WHERE c.dataConsulta >= FUNCTION('ADD_MONTHS', CURRENT_DATE, :mesesNegativos)
                  AND c.status = 'REALIZADA'
            )
            """)
    Page<Pet> findPetsSemConsultaRecente(
            @Param("mesesNegativos") int mesesNegativos,
            Pageable pageable
    );
}
