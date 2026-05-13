package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRESCRICOES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Associa medicamentos a tratamentos com posologia detalhada. Resolve o relacionamento N:N entre TRATAMENTOS e MEDICAMENTOS")
public class Prescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_prescricoes")
    @SequenceGenerator(name = "seq_prescricoes", sequenceName = "SEQ_PRESCRICOES", allocationSize = 1)
    @Column(name = "ID_PRESCRICAO")
    @Schema(description = "Identificador único", example = "1")
    private Long idPrescricao;

    @Column(name = "DOSAGEM", nullable = false, length = 50)
    @Schema(description = "Ex.: 10mg, 1 comprimido", example = "10mg")
    private String dosagem;

    @Column(name = "FREQUENCIA", nullable = false, length = 50)
    @Schema(description = "Ex.: 2x ao dia, a cada 8h", example = "2x ao dia")
    private String frequencia;

    @Column(name = "DURACAO_DIAS", precision = 5)
    @Schema(description = "Duração em dias", example = "7")
    private Integer duracaoDias;

    @Column(name = "INSTRUCOES", length = 500)
    @Schema(description = "Instruções adicionais", example = "Administrar após alimentação")
    private String instrucoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TRATAMENTO", nullable = false)
    @Schema(description = "Tratamento associado")
    private Tratamento tratamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MEDICAMENTO", nullable = false)
    @Schema(description = "Medicamento prescrito")
    private Medicamento medicamento;
}
