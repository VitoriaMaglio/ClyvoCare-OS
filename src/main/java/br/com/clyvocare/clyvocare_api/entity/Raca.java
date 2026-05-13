package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RACAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Raças ou variedades dentro de cada espécie")
public class Raca {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_racas")
    @SequenceGenerator(name = "seq_racas", sequenceName = "SEQ_RACAS", allocationSize = 1)
    @Column(name = "ID_RACA")
    @Schema(description = "Identificador único", example = "1")
    private Long idRaca;

    @Column(name = "NOME", nullable = false, length = 100)
    @Schema(description = "Nome da raça", example = "Golden Retriever")
    private String nome;

    @Column(name = "PORTE", length = 15)
    @Schema(description = "Pequeno / Médio / Grande", example = "Grande")
    private String porte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESPECIE", nullable = false)
    @Schema(description = "Espécie à qual pertence")
    private Especie especie;
}
