package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados retornados de uma vacinação")
public class VacinacaoResponseDTO {

    @Schema(description = "ID da vacinação", example = "1")
    private Long idVacinacao;

    @Schema(description = "Data da aplicação")
    private LocalDate dataAplicacao;

    @Schema(description = "Número do lote", example = "L2025A001")
    private String lote;

    @Schema(description = "Data prevista para próxima dose")
    private LocalDate proximaDose;

    @Schema(description = "Validade da vacina aplicada")
    private LocalDate validade;

    @Schema(description = "Vacina atrasada?", example = "true")
    private boolean atrasada;

    @Schema(description = "Dias de atraso (se atrasada)", example = "30")
    private Long diasAtraso;

    @Schema(description = "Nome do pet", example = "Rex")
    private String nomePet;

    @Schema(description = "ID do pet", example = "1")
    private Long idPet;

    @Schema(description = "Nome da vacina", example = "V10")
    private String nomeVacina;

    @Schema(description = "Nome do veterinário responsável")
    private String nomeVeterinario;
}
