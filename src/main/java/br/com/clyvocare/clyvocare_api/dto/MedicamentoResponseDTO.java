package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados retornados de um medicamento")
public class MedicamentoResponseDTO {


    @Schema(description = "ID do medicamento", example = "1")
    private Long idMedicamento;

    @Schema(description = "Nome comercial", example = "Amoxicilina 500mg")
    private String nome;

    @Schema(description = "Substância ativa", example = "Amoxicilina")
    private String principioAtivo;

    @Schema(description = "Laboratório fabricante", example = "Eurofarma")
    private String fabricante;
}
