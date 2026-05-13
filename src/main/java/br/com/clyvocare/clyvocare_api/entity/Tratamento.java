package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "TRATAMENTOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Tratamentos prescritos após consulta, com período de vigência")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tratamentos")
    @SequenceGenerator(name = "seq_tratamentos", sequenceName = "SEQ_TRATAMENTOS", allocationSize = 1)
    @Column(name = "ID_TRATAMENTO")
    @Schema(description = "Identificador único", example = "1")
    private Long idTratamento;

    @Column(name = "DESCRICAO", nullable = false, length = 500)
    @Schema(description = "Descrição do tratamento")
    private String descricao;

    @Column(name = "DATA_INICIO", nullable = false)
    @Schema(description = "Início do tratamento")
    private LocalDate dataInicio;

    @Column(name = "DATA_FIM")
    @Schema(description = "Previsão de término")
    private LocalDate dataFim;

    @Column(name = "STATUS", length = 20)
    @Schema(description = "Situação atual (ATIVO/CONCLUIDO/SUSPENSO)", example = "ATIVO")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PET", nullable = false)
    @Schema(description = "Animal em tratamento")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CONSULTA")
    @Schema(description = "Consulta que originou")
    private Consulta consulta;
}
