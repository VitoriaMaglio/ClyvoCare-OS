package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados retornados de uma cidade")
public class CidadeResponseDTO {

    @Schema(description = "ID da cidade", example = "1")
    private Long idCidade;

    @Schema(description = "Nome do município", example = "São Paulo")
    private String nome;

    @Schema(description = "Sigla do estado", example = "SP")
    private String estado;

    @Schema(description = "Região geográfica", example = "Sudeste")
    private String regiao;
}
