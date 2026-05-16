package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados para registro de exame")
public class ExameRequestDTO {

    @NotBlank(message = "Tipo do exame é obrigatório")
    @Size(max = 100)
    @Schema(description = "Tipo do exame", example = "Hemograma")
    private String tipoExame;

    @NotNull(message = "Data de solicitação é obrigatória")
    @Schema(description = "Data da solicitação", example = "2025-05-10")
    private LocalDate dataSolicitacao;

    @Schema(description = "Data de liberação do resultado", example = "2025-05-12")
    private LocalDate dataResultado;

    @Schema(description = "Laudo ou descrição do resultado")
    private String resultado;

    @Size(max = 500)
    @Schema(description = "Link para PDF/imagem do laudo")
    private String arquivoUrl;

    @Size(max = 150)
    @Schema(description = "Nome do laboratório", example = "LabVet SP")
    private String laboratorio;

    @NotNull(message = "Pet é obrigatório")
    @Schema(description = "ID do pet", example = "1")
    private Long idPet;

    @Schema(description = "ID da consulta que solicitou o exame", example = "1")
    private Long idConsulta;
}
