package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados para registro de peso do pet")
public class HistoricoPesoRequestDTO {

    @NotNull(message = "Data de registro é obrigatória")
    @Schema(description = "Data da pesagem", example = "2025-05-15")
    private LocalDate dataRegistro;

    @NotNull(message = "Peso é obrigatório")
    @DecimalMin(value = "0.001", message = "Peso deve ser maior que zero")
    @Schema(description = "Peso em kg", example = "12.500")
    private BigDecimal peso;

    @NotNull(message = "Pet é obrigatório")
    @Schema(description = "ID do pet pesado", example = "1")
    private Long idPet;

    @Schema(description = "ID da consulta associada (opcional)", example = "1")
    private Long idConsulta;
}
