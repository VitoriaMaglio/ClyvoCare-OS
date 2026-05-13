package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CONSULTAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Registra cada atendimento clínico. Relaciona pet, veterinário e clínica")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_consultas")
    @SequenceGenerator(name = "seq_consultas", sequenceName = "SEQ_CONSULTAS", allocationSize = 1)
    @Column(name = "ID_CONSULTA")
    @Schema(description = "Identificador único", example = "1")
    private Long idConsulta;

    @Column(name = "DATA_CONSULTA", nullable = false)
    @Schema(description = "Data e hora do atendimento")
    private LocalDate dataConsulta;

    @Column(name = "TIPO", length = 20)
    @Schema(description = "Tipo do atendimento (ROTINA/RETORNO/EMERGENCIA)", example = "ROTINA")
    private String tipo;

    @Column(name = "MOTIVO", length = 500)
    @Schema(description = "Queixa principal / motivo da consulta")
    private String motivo;

    @Column(name = "DIAGNOSTICO", length = 1000)
    @Schema(description = "Diagnóstico registrado pelo veterinário")
    private String diagnostico;

    @Lob
    @Column(name = "OBSERVACOES")
    @Schema(description = "Notas livres do veterinário")
    private String observacoes;

    @Column(name = "PESO_NA_CONSULTA", precision = 6, scale = 3)
    @Schema(description = "Peso do animal na data da consulta", example = "12.500")
    private BigDecimal pesoNaConsulta;

    @Column(name = "STATUS", length = 20)
    @Schema(description = "Situação da consulta (AGENDADA/REALIZADA/CANCELADA)", example = "REALIZADA")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PET", nullable = false)
    @Schema(description = "Animal atendido")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VETERINARIO")
    @Schema(description = "Veterinário responsável")
    private Veterinario veterinario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLINICA")
    @Schema(description = "Clínica do atendimento")
    private Clinica clinica;
}
