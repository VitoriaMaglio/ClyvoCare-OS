package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FASES_VIDA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Fases etárias (Filhote, Adulto, Idoso) por espécie, com limites em meses")
public class FaseVida {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fases_vida")
    @SequenceGenerator(name = "seq_fases_vida", sequenceName = "SEQ_FASES_VIDA", allocationSize = 1)
    @Column(name = "ID_FASE")
    @Schema(description = "Identificador único", example = "1")
    private Long idFase;

    @Column(name = "NOME", nullable = false, length = 30)
    @Schema(description = "Ex.: Filhote, Adulto, Idoso", example = "Filhote")
    private String nome;

    @Column(name = "IDADE_MIN_MESES", nullable = false, precision = 5)
    @Schema(description = "Início da fase em meses", example = "0")
    private Integer idadeMinMeses;

    @Column(name = "IDADE_MAX_MESES", precision = 5)
    @Schema(description = "Fim da fase (NULL = sem limite)", example = "12")
    private Integer idadeMaxMeses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESPECIE", nullable = false)
    @Schema(description = "Espécie de referência")
    private Especie especie;
}
