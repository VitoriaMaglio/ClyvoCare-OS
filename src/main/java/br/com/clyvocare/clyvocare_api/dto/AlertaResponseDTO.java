package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Alerta preventivo gerado automaticamente para o pet")
public class AlertaResponseDTO {

    @Schema(description = "Tipo do alerta", example = "VACINA_ATRASADA")
    private String tipo;

    @Schema(description = "Nível de urgência: BAIXO, MEDIO ou ALTO", example = "ALTO")
    private String urgencia;

    @Schema(description = "Título curto do alerta", example = "Vacina V10 atrasada")
    private String titulo;

    @Schema(description = "Descrição detalhada do alerta",
            example = "A vacina V10 está atrasada há 45 dias. Agende a vacinação o quanto antes.")
    private String descricao;

    @Schema(description = "Ação recomendada", example = "Agendar vacinação")
    private String acaoRecomendada;
}
