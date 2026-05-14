package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Score de risco calculado para o pet")
public class RiscoResponseDTO {

    @Schema(description = "ID do pet", example = "1")
    private Long idPet;

    @Schema(description = "Nome do pet", example = "Rex")
    private String nomePet;

    @Schema(description = "Nível de risco calculado: BAIXO, MEDIO ou ALTO", example = "MEDIO")
    private String nivelRisco;

    @Schema(description = "Pontuação numérica de risco (0-100)", example = "55")
    private int pontuacao;

    @Schema(description = "Fatores que contribuíram para o risco")
    private List<String> fatoresRisco;

    @Schema(description = "Vacinas atrasadas", example = "2")
    private int vacinasAtrasadas;

    @Schema(description = "Tratamentos ativos há mais de 30 dias", example = "1")
    private int tratamentosAbandonados;

    @Schema(description = "Meses sem consulta", example = "8")
    private int mesesSemConsulta;

    @Schema(description = "Pet obeso?", example = "false")
    private boolean obeso;
}
