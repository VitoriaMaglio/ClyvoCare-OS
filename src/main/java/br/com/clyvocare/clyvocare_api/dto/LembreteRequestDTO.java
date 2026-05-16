package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Dados para criação de lembrete")
public class LembreteRequestDTO {

    @NotBlank(message = "Tipo é obrigatório")
    @Pattern(regexp = "VACINA|CONSULTA|EXAME|MEDICAMENTO",
             message = "Tipo deve ser VACINA, CONSULTA, EXAME ou MEDICAMENTO")
    @Schema(description = "Categoria do lembrete", example = "VACINA")
    private String tipo;

    @NotNull(message = "Data do evento é obrigatória")
    @Schema(description = "Data do evento a ser lembrado", example = "2025-06-01")
    private LocalDate dataEvento;

    @NotBlank(message = "Mensagem é obrigatória")
    @Size(max = 500)
    @Schema(description = "Texto do lembrete", example = "Vacina V10 do Rex vence em 01/06/2025")
    private String mensagem;

    @Pattern(regexp = "PENDENTE|ENVIADO|CONFIRMADO",
             message = "Status deve ser PENDENTE, ENVIADO ou CONFIRMADO")
    @Schema(description = "Status do envio", example = "PENDENTE")
    private String status;

    @Pattern(regexp = "WHATSAPP|PUSH|EMAIL",
             message = "Canal deve ser WHATSAPP, PUSH ou EMAIL")
    @Schema(description = "Canal de envio", example = "WHATSAPP")
    private String canal;

    @NotNull(message = "Pet é obrigatório")
    @Schema(description = "ID do pet referenciado", example = "1")
    private Long idPet;

    @NotNull(message = "Tutor é obrigatório")
    @Schema(description = "ID do tutor destinatário", example = "1")
    private Long idTutor;
}
