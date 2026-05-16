package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados retornados de um lembrete")
public class LembreteResponseDTO {

    @Schema(description = "ID do lembrete", example = "1")
    private Long idLembrete;

    @Schema(description = "Tipo do lembrete", example = "VACINA")
    private String tipo;

    @Schema(description = "Data do evento")
    private LocalDate dataEvento;

    @Schema(description = "Mensagem do lembrete")
    private String mensagem;

    @Schema(description = "Status: PENDENTE, ENVIADO ou CONFIRMADO", example = "PENDENTE")
    private String status;

    @Schema(description = "Canal: WHATSAPP, PUSH ou EMAIL", example = "WHATSAPP")
    private String canal;

    @Schema(description = "Evento já passou?", example = "false")
    private boolean vencido;

    @Schema(description = "ID do pet", example = "1")
    private Long idPet;

    @Schema(description = "Nome do pet", example = "Rex")
    private String nomePet;

    @Schema(description = "ID do tutor", example = "1")
    private Long idTutor;

    @Schema(description = "Nome do tutor", example = "Maria da Silva")
    private String nomeTutor;
}
