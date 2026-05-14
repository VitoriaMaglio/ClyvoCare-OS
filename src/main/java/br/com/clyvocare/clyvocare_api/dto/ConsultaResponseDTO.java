package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados retornados de uma consulta")
public class ConsultaResponseDTO {

    @Schema(description = "ID da consulta", example = "1")
    private Long idConsulta;

    @Schema(description = "Data do atendimento")
    private LocalDate dataConsulta;

    @Schema(description = "Tipo: ROTINA, RETORNO ou EMERGENCIA", example = "ROTINA")
    private String tipo;

    @Schema(description = "Motivo da consulta")
    private String motivo;

    @Schema(description = "Diagnóstico registrado")
    private String diagnostico;

    @Schema(description = "Observações do veterinário")
    private String observacoes;

    @Schema(description = "Peso na data da consulta", example = "12.500")
    private BigDecimal pesoNaConsulta;

    @Schema(description = "Status: AGENDADA, REALIZADA ou CANCELADA", example = "REALIZADA")
    private String status;

    @Schema(description = "Nome do pet", example = "Rex")
    private String nomePet;

    @Schema(description = "ID do pet", example = "1")
    private Long idPet;

    @Schema(description = "Nome do veterinário", example = "Dr. João Souza")
    private String nomeVeterinario;

    @Schema(description = "Nome da clínica", example = "Clínica Vet Amigos")
    private String nomeClinica;
}
