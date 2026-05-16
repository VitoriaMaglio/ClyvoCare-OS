package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados para cadastro de vacina")
public class VacinaRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 150)
    @Schema(description = "Nome comercial ou científico", example = "V10")
    private String nome;

    @Size(max = 100)
    @Schema(description = "Laboratório fabricante", example = "MSD Saúde Animal")
    private String fabricante;

    @Size(max = 300)
    @Schema(description = "Doenças que a vacina previne", example = "Cinomose, Parvovirose, Hepatite")
    private String doencasPrevine;

    @Positive(message = "Intervalo de reforço deve ser positivo")
    @Schema(description = "Intervalo padrão de reforço em dias", example = "365")
    private Integer intervaloReforcoDias;

    @NotNull(message = "Espécie é obrigatória")
    @Schema(description = "ID da espécie alvo", example = "1")
    private Long idEspecie;
}
