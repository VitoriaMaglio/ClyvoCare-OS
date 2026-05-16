package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados para criação de protocolo de vacinação")
public class ProtocoloVacinacaoRequestDTO {

    @NotBlank(message = "Obrigatória é obrigatório")
    @Pattern(regexp = "S|N", message = "Obrigatória deve ser S ou N")
    @Schema(description = "Vacina obrigatória no protocolo?", example = "S")
    private String obrigatoria;

    @Positive(message = "Idade de aplicação deve ser positiva")
    @Schema(description = "Idade ideal de aplicação em dias", example = "60")
    private Integer idadeAplicacaoDias;

    @NotNull(message = "Vacina é obrigatória")
    @Schema(description = "ID da vacina", example = "1")
    private Long idVacina;

    @NotNull(message = "Fase de vida é obrigatória")
    @Schema(description = "ID da fase de vida", example = "1")
    private Long idFase;
}
