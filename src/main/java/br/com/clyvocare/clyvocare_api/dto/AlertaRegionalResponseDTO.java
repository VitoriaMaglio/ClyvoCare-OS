package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Alerta epidemiológico regional")
public class AlertaRegionalResponseDTO {

    @Schema(description = "ID do alerta", example = "1")
    private Long idAlerta;

    @Schema(description = "Nome da doença em alerta", example = "Leishmaniose")
    private String doenca;

    @Schema(description = "Nível de risco: BAIXO, MEDIO ou ALTO", example = "ALTO")
    private String nivelRisco;

    @Schema(description = "Início do período de alerta")
    private LocalDate dataInicio;

    @Schema(description = "Término do alerta (null = ainda ativo)")
    private LocalDate dataFim;

    @Schema(description = "Alerta ainda ativo?", example = "true")
    private boolean ativo;

    @Schema(description = "Detalhamento do alerta")
    private String descricao;

    @Schema(description = "Órgão ou fonte dos dados", example = "MAPA")
    private String fonte;

    @Schema(description = "ID da cidade", example = "1")
    private Long idCidade;

    @Schema(description = "Nome da cidade", example = "São Paulo")
    private String nomeCidade;

    @Schema(description = "Estado (UF)", example = "SP")
    private String estado;

    @Schema(description = "Região geográfica", example = "Sudeste")
    private String regiao;
}
