package br.com.clyvocare.clyvocare_api.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados retornados de uma prescrição")
public class PrescricaoResponseDTO {

    @Schema(description = "ID da prescrição", example = "1")
    private Long idPrescricao;

    @Schema(description = "Dosagem prescrita", example = "10mg")
    private String dosagem;

    @Schema(description = "Frequência de administração", example = "2x ao dia")
    private String frequencia;

    @Schema(description = "Duração em dias", example = "7")
    private Integer duracaoDias;

    @Schema(description = "Instruções adicionais", example = "Administrar após alimentação")
    private String instrucoes;

    @Schema(description = "ID do tratamento", example = "1")
    private Long idTratamento;

    @Schema(description = "Descrição do tratamento")
    private String descricaoTratamento;

    @Schema(description = "ID do medicamento", example = "3")
    private Long idMedicamento;

    @Schema(description = "Nome do medicamento", example = "Amoxicilina 500mg")
    private String nomeMedicamento;

    @Schema(description = "Princípio ativo", example = "Amoxicilina")
    private String principioAtivo;

    @Schema(description = "Nome do pet em tratamento", example = "Rex")
    private String nomePet;
}
