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
@Schema(description = "Dados para registro de vacinação")
public class VacinacaoRequestDTO {

    @NotNull(message = "Data de aplicação é obrigatória")
    @Schema(description = "Data da aplicação", example = "2025-04-20")
    private LocalDate dataAplicacao;

    @Size(max = 50)
    @Schema(description = "Número do lote", example = "L2025A001")
    private String lote;

    @Schema(description = "Data calculada para próxima dose", example = "2026-04-20")
    private LocalDate proximaDose;

    @Schema(description = "Validade da vacina", example = "2027-01-01")
    private LocalDate validade;

    @NotNull(message = "Pet é obrigatório")
    @Schema(description = "ID do pet vacinado", example = "1")
    private Long idPet;

    @NotNull(message = "Vacina é obrigatória")
    @Schema(description = "ID da vacina aplicada", example = "2")
    private Long idVacina;

    @Schema(description = "ID da consulta associada (opcional)", example = "5")
    private Long idConsulta;

    @Schema(description = "ID do veterinário responsável", example = "1")
    private Long idVeterinario;
}
