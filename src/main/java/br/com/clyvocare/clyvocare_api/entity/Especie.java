package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ESPECIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Catálogo de espécies animais atendidas pela plataforma")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_especies")
    @SequenceGenerator(name = "seq_especies", sequenceName = "SEQ_ESPECIES", allocationSize = 1)
    @Column(name = "ID_ESPECIE")
    @Schema(description = "Identificador único", example = "1")
    private Long idEspecie;

    @Column(name = "NOME", nullable = false, unique = true, length = 50)
    @Schema(description = "Nome da espécie (Cão, Gato, Ave, etc.)", example = "Cão")
    private String nome;

    @Column(name = "DESCRICAO", length = 255)
    @Schema(description = "Observações gerais sobre a espécie")
    private String descricao;
}
