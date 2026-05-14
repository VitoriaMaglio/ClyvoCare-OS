package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para registro de consulta")
public class ConsultaRequestDTO {

    @NotNull(message = "Data da consulta é obrigatória")
    @Schema(description = "Data do atendimento", example = "2025-05-10")
    private LocalDate dataConsulta;

    @NotBlank(message = "Tipo é obrigatório")
    @Pattern(regexp = "ROTINA|RETORNO|EMERGENCIA", message = "Tipo deve ser ROTINA, RETORNO ou EMERGENCIA")
    @Schema(description = "Tipo do atendimento", example = "ROTINA")
    private String tipo;

    @Size(max = 500)
    @Schema(description = "Motivo/queixa principal", example = "Animal com febre há 2 dias")
    private String motivo;

    @Size(max = 1000)
    @Schema(description = "Diagnóstico do veterinário")
    private String diagnostico;

    @Schema(description = "Observações livres")
    private String observacoes;

    @DecimalMin(value = "0.001", message = "Peso deve ser maior que zero")
    @Schema(description = "Peso do animal na consulta", example = "12.500")
    private BigDecimal pesoNaConsulta;

    @NotBlank(message = "Status é obrigatório")
    @Pattern(regexp = "AGENDADA|REALIZADA|CANCELADA", message = "Status deve ser AGENDADA, REALIZADA ou CANCELADA")
    @Schema(description = "Status da consulta", example = "REALIZADA")
    private String status;

    @NotNull(message = "Pet é obrigatório")
    @Schema(description = "ID do pet atendido", example = "1")
    private Long idPet;

    @Schema(description = "ID do veterinário", example = "2")
    private Long idVeterinario;

    @Schema(description = "ID da clínica", example = "1")
    private Long idClinica;
}
