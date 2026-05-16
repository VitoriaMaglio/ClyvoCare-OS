package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados retornados de um registro de peso")
public class HistoricoPesoResponseDTO {

    @Schema(description = "ID do registro", example = "1")
    private Long idHistorico;

    @Schema(description = "Data da pesagem")
    private LocalDate dataRegistro;

    @Schema(description = "Peso em kg", example = "12.500")
    private BigDecimal peso;

    @Schema(description = "Variação em relação ao registro anterior (kg)", example = "-0.300")
    private BigDecimal variacao;

    @Schema(description = "ID do pet", example = "1")
    private Long idPet;

    @Schema(description = "Nome do pet", example = "Rex")
    private String nomePet;

    @Schema(description = "ID da consulta associada", example = "1")
    private Long idConsulta;
}
