package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Histórico longitudinal completo de saúde do pet")
public class HistoricoResponseDTO {

    @Schema(description = "Dados básicos do pet")
    private PetResponseDTO pet;

    @Schema(description = "Score de risco atual")
    private RiscoResponseDTO risco;

    @Schema(description = "Alertas ativos")
    private List<AlertaResponseDTO> alertas;

    @Schema(description = "Histórico de consultas (mais recentes primeiro)")
    private List<ConsultaResponseDTO> consultas;

    @Schema(description = "Histórico de vacinações")
    private List<VacinacaoResponseDTO> vacinacoes;

    @Schema(description = "Tratamentos (ativos e concluídos)")
    private List<TratamentoResponseDTO> tratamentos;

    @Schema(description = "Total de consultas realizadas", example = "12")
    private int totalConsultas;

    @Schema(description = "Total de vacinas aplicadas", example = "8")
    private int totalVacinas;

    @Schema(description = "Tratamentos ativos", example = "1")
    private int tratamentosAtivos;
}
