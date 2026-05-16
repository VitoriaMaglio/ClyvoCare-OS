package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados retornados de um protocolo de vacinação")
public class ProtocoloVacinacaoResponseDTO {

    @Schema(description = "ID do protocolo", example = "1")
    private Long idProtocolo;

    @Schema(description = "Vacina obrigatória?", example = "S")
    private String obrigatoria;

    @Schema(description = "Idade ideal de aplicação em dias", example = "60")
    private Integer idadeAplicacaoDias;

    @Schema(description = "ID da vacina", example = "1")
    private Long idVacina;

    @Schema(description = "Nome da vacina", example = "V10")
    private String nomeVacina;

    @Schema(description = "ID da fase de vida", example = "1")
    private Long idFase;

    @Schema(description = "Nome da fase de vida", example = "Filhote")
    private String nomeFase;

    @Schema(description = "Espécie do protocolo", example = "Cão")
    private String nomeEspecie;
}
