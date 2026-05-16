package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados para cadastro de raça")
public class RacaRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100)
    @Schema(description = "Nome da raça", example = "Golden Retriever")
    private String nome;

    @Pattern(regexp = "Pequeno|Médio|Grande", message = "Porte deve ser Pequeno, Médio ou Grande")
    @Schema(description = "Porte do animal", example = "Grande")
    private String porte;

    @NotNull(message = "Espécie é obrigatória")
    @Schema(description = "ID da espécie", example = "1")
    private Long idEspecie;
}
