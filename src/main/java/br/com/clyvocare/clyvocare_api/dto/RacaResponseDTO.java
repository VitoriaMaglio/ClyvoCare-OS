package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados retornados de uma raça")
public class RacaResponseDTO {

    @Schema(description = "ID da raça", example = "1")
    private Long idRaca;

    @Schema(description = "Nome da raça", example = "Golden Retriever")
    private String nome;

    @Schema(description = "Porte", example = "Grande")
    private String porte;

    @Schema(description = "ID da espécie", example = "1")
    private Long idEspecie;

    @Schema(description = "Nome da espécie", example = "Cão")
    private String nomeEspecie;
}
