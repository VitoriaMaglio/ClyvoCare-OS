package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Recomendações preventivas baseadas na fase de vida do pet")
public class RecomendacaoResponseDTO {

    @Schema(description = "ID do pet", example = "1")
    private Long idPet;

    @Schema(description = "Nome do pet", example = "Rex")
    private String nomePet;

    @Schema(description = "Espécie", example = "Cão")
    private String especie;

    @Schema(description = "Fase de vida atual", example = "Adulto")
    private String faseVida;

    @Schema(description = "Idade em meses", example = "36")
    private Integer idadeMeses;

    @Schema(description = "Lista de recomendações para a fase atual")
    private List<String> recomendacoes;
}
