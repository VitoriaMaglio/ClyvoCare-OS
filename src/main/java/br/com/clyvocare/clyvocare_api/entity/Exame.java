package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "EXAMES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Exames laboratoriais ou de imagem solicitados ou realizados para um animal")
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_exames")
    @SequenceGenerator(name = "seq_exames", sequenceName = "SEQ_EXAMES", allocationSize = 1)
    @Column(name = "ID_EXAME")
    @Schema(description = "Identificador único", example = "1")
    private Long idExame;

    @Column(name = "TIPO_EXAME", nullable = false, length = 100)
    @Schema(description = "Ex.: Hemograma, Raio-X, Ultrassom", example = "Hemograma")
    private String tipoExame;

    @Column(name = "DATA_SOLICITACAO", nullable = false)
    @Schema(description = "Data da solicitação")
    private LocalDate dataSolicitacao;

    @Column(name = "DATA_RESULTADO")
    @Schema(description = "Data de liberação do resultado")
    private LocalDate dataResultado;

    @Lob
    @Column(name = "RESULTADO")
    @Schema(description = "Laudo ou descrição do resultado")
    private String resultado;

    @Column(name = "ARQUIVO_URL", length = 500)
    @Schema(description = "Link para PDF/imagem do laudo")
    private String arquivoUrl;

    @Column(name = "LABORATORIO", length = 150)
    @Schema(description = "Nome do laboratório", example = "LabVet SP")
    private String laboratorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PET", nullable = false)
    @Schema(description = "Animal do exame")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CONSULTA")
    @Schema(description = "Consulta que solicitou o exame")
    private Consulta consulta;
}
