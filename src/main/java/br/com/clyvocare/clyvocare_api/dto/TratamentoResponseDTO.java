package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados retornados de um tratamento")
public class TratamentoResponseDTO {

    @Schema(description = "ID do tratamento", example = "1")
    private Long idTratamento;

    @Schema(description = "Descrição do tratamento")
    private String descricao;

    @Schema(description = "Data de início")
    private LocalDate dataInicio;

    @Schema(description = "Data de término prevista")
    private LocalDate dataFim;

    @Schema(description = "Status: ATIVO, CONCLUIDO ou SUSPENSO", example = "ATIVO")
    private String status;

    @Schema(description = "Dias em andamento", example = "5")
    private Long diasEmAndamento;

    @Schema(description = "Tratamento abandonado (ativo há mais de 30 dias sem conclusão)?", example = "false")
    private boolean abandonado;

    @Schema(description = "Nome do pet", example = "Rex")
    private String nomePet;

    @Schema(description = "ID do pet", example = "1")
    private Long idPet;
}
