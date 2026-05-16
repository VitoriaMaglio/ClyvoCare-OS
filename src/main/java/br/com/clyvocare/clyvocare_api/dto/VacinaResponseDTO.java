package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados retornados de uma vacina")
public class VacinaResponseDTO {

    @Schema(description = "ID da vacina", example = "1")
    private Long idVacina;

    @Schema(description = "Nome comercial ou científico", example = "V10")
    private String nome;

    @Schema(description = "Laboratório fabricante", example = "MSD Saúde Animal")
    private String fabricante;

    @Schema(description = "Doenças que a vacina previne")
    private String doencasPrevine;

    @Schema(description = "Intervalo padrão de reforço em dias", example = "365")
    private Integer intervaloReforcoDias;

    @Schema(description = "ID da espécie alvo", example = "1")
    private Long idEspecie;

    @Schema(description = "Nome da espécie alvo", example = "Cão")
    private String nomeEspecie;
}
