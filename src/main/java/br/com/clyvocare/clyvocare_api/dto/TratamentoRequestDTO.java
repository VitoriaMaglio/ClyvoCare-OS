package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para registro de tratamento")
public class TratamentoRequestDTO {

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 500)
    @Schema(description = "Descrição do tratamento", example = "Antibioticoterapia para infecção respiratória")
    private String descricao;

    @NotNull(message = "Data de início é obrigatória")
    @Schema(description = "Data de início do tratamento", example = "2025-05-01")
    private LocalDate dataInicio;

    @Schema(description = "Data prevista de término", example = "2025-05-10")
    private LocalDate dataFim;

    @NotBlank(message = "Status é obrigatório")
    @Pattern(regexp = "ATIVO|CONCLUIDO|SUSPENSO", message = "Status deve ser ATIVO, CONCLUIDO ou SUSPENSO")
    @Schema(description = "Status do tratamento", example = "ATIVO")
    private String status;

    @NotNull(message = "Pet é obrigatório")
    @Schema(description = "ID do pet em tratamento", example = "1")
    private Long idPet;

    @Schema(description = "ID da consulta que originou o tratamento", example = "3")
    private Long idConsulta;
}
