package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados retornados de uma fase de vida")
public class FaseVidaResponseDTO {

    @Schema(description = "ID da fase", example = "1")
    private Long idFase;

    @Schema(description = "Nome da fase", example = "Filhote")
    private String nome;

    @Schema(description = "Idade mínima em meses", example = "0")
    private Integer idadeMinMeses;

    @Schema(description = "Idade máxima em meses (null = sem limite)", example = "12")
    private Integer idadeMaxMeses;

    @Schema(description = "ID da espécie", example = "1")
    private Long idEspecie;

    @Schema(description = "Nome da espécie", example = "Cão")
    private String nomeEspecie;
}
