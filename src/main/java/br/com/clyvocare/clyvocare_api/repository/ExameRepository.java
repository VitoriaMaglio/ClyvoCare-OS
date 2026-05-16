package br.com.clyvocare.clyvocare_api.repository;

import br.com.clyvocare.clyvocare_api.entity.Exame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExameRepository extends JpaRepository<Exame, Long> {

    Page<Exame> findByPetIdPetOrderByDataSolicitacaoDesc(Long idPet, Pageable pageable);

    Page<Exame> findByTipoExameContainingIgnoreCase(String tipoExame, Pageable pageable);

    Page<Exame> findByConsultaIdConsulta(Long idConsulta, Pageable pageable);

    // Exames sem resultado ainda
    Page<Exame> findByDataResultadoIsNull(Pageable pageable);

    boolean existsByPetIdPet(Long idPet);
}
