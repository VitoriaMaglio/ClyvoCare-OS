package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para registro de prescrição de medicamento em um tratamento")
public class PrescricaoRequestDTO {

    @NotBlank(message = "Dosagem é obrigatória")
    @Size(max = 50)
    @Schema(description = "Dosagem prescrita", example = "10mg")
    private String dosagem;

    @NotBlank(message = "Frequência é obrigatória")
    @Size(max = 50)
    @Schema(description = "Frequência de administração", example = "2x ao dia")
    private String frequencia;

    @Positive(message = "Duração deve ser um número positivo")
    @Schema(description = "Duração em dias", example = "7")
    private Integer duracaoDias;

    @Size(max = 500)
    @Schema(description = "Instruções adicionais", example = "Administrar após alimentação")
    private String instrucoes;

    @NotNull(message = "Tratamento é obrigatório")
    @Schema(description = "ID do tratamento associado", example = "1")
    private Long idTratamento;

    @NotNull(message = "Medicamento é obrigatório")
    @Schema(description = "ID do medicamento prescrito", example = "3")
    private Long idMedicamento;
}
