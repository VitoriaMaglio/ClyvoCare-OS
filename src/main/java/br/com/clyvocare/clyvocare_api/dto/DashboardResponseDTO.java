package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Métricas gerais do dashboard clínico")
public class DashboardResponseDTO {

    @Schema(description = "Total de tutores cadastrados", example = "150")
    private long totalTutores;

    @Schema(description = "Total de pets cadastrados", example = "210")
    private long totalPets;

    @Schema(description = "Pets com risco ALTO", example = "18")
    private long petsRiscoAlto;

    @Schema(description = "Pets com risco MEDIO", example = "45")
    private long petsRiscoMedio;

    @Schema(description = "Pets com risco BAIXO", example = "147")
    private long petsRiscoBaixo;

    @Schema(description = "Total de vacinas atrasadas", example = "32")
    private long vacinasAtrasadas;

    @Schema(description = "Tratamentos ativos no momento", example = "27")
    private long tratamentosAtivos;

    @Schema(description = "Tratamentos com suspeita de abandono", example = "5")
    private long tratamentosAbandonados;

    @Schema(description = "Consultas agendadas (não realizadas)", example = "14")
    private long consultasAgendadas;

    @Schema(description = "Taxa de adesão a tratamentos (%)", example = "82.5")
    private double taxaAdesaoTratamentos;
}
