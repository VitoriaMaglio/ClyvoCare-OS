package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados para cadastro de espécie")
public class EspecieRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 50)
    @Schema(description = "Nome da espécie", example = "Cão")
    private String nome;

    @Size(max = 255)
    @Schema(description = "Descrição geral da espécie", example = "Mamífero doméstico")
    private String descricao;
}
