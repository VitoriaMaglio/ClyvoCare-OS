package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para cadastro ou atualização de medicamento")
public class MedicamentoRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 150)
    @Schema(description = "Nome comercial", example = "Amoxicilina 500mg")
    private String nome;

    @Size(max = 150)
    @Schema(description = "Substância ativa", example = "Amoxicilina")
    private String principioAtivo;

    @Size(max = 100)
    @Schema(description = "Laboratório fabricante", example = "Eurofarma")
    private String fabricante;
}
