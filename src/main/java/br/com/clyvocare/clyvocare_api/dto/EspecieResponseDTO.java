package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados retornados de uma espécie")
public class EspecieResponseDTO {

    @Schema(description = "ID da espécie", example = "1")
    private Long idEspecie;

    @Schema(description = "Nome da espécie", example = "Cão")
    private String nome;

    @Schema(description = "Descrição geral", example = "Mamífero doméstico")
    private String descricao;
}
