package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados retornados de um exame")
public class ExameResponseDTO {

    @Schema(description = "ID do exame", example = "1")
    private Long idExame;

    @Schema(description = "Tipo do exame", example = "Hemograma")
    private String tipoExame;

    @Schema(description = "Data da solicitação")
    private LocalDate dataSolicitacao;

    @Schema(description = "Data de liberação do resultado")
    private LocalDate dataResultado;

    @Schema(description = "Resultado disponível?", example = "true")
    private boolean resultadoDisponivel;

    @Schema(description = "Laudo ou descrição do resultado")
    private String resultado;

    @Schema(description = "Link para PDF/imagem do laudo")
    private String arquivoUrl;

    @Schema(description = "Nome do laboratório", example = "LabVet SP")
    private String laboratorio;

    @Schema(description = "ID do pet", example = "1")
    private Long idPet;

    @Schema(description = "Nome do pet", example = "Rex")
    private String nomePet;

    @Schema(description = "ID da consulta associada", example = "1")
    private Long idConsulta;
}
