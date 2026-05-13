package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "ALERTAS_REGIONAIS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Alertas epidemiológicos por cidade, gerados pelo módulo de IA para notificar tutores e clínicas da região")
public class AlertaRegional {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_alertas_regionais")
    @SequenceGenerator(name = "seq_alertas_regionais", sequenceName = "SEQ_ALERTAS_REGIONAIS", allocationSize = 1)
    @Column(name = "ID_ALERTA")
    @Schema(description = "Identificador único", example = "1")
    private Long idAlerta;

    @Column(name = "DOENCA", nullable = false, length = 150)
    @Schema(description = "Nome da doença em alerta", example = "Leishmaniose")
    private String doenca;

    @Column(name = "NIVEL_RISCO", length = 10)
    @Schema(description = "Nível de criticidade (BAIXO/MEDIO/ALTO)", example = "ALTO")
    private String nivelRisco;

    @Column(name = "DATA_INICIO", nullable = false)
    @Schema(description = "Início do período de alerta")
    private LocalDate dataInicio;

    @Column(name = "DATA_FIM")
    @Schema(description = "Término do alerta (NULL = ativo)")
    private LocalDate dataFim;

    @Column(name = "DESCRICAO", length = 1000)
    @Schema(description = "Detalhamento do alerta")
    private String descricao;

    @Column(name = "FONTE", length = 200)
    @Schema(description = "Órgão ou fonte dos dados", example = "MAPA - Ministério da Agricultura")
    private String fonte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CIDADE", nullable = false)
    @Schema(description = "Cidade com alerta ativo")
    private Cidade cidade;
}
