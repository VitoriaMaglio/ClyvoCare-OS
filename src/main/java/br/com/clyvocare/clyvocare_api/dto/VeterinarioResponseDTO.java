package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados retornados de um veterinário")
public class VeterinarioResponseDTO {


    @Schema(description = "ID do veterinário", example = "1")
    private Long idVeterinario;

    @Schema(description = "Nome completo", example = "Dr. João Souza")
    private String nome;

    @Schema(description = "Registro no conselho", example = "CRMV-SP 12345")
    private String crmv;

    @Schema(description = "Área de atuação principal", example = "Cardiologia")
    private String especialidade;

    @Schema(description = "E-mail profissional", example = "joao@vetclinica.com")
    private String email;

    @Schema(description = "Telefone de contato", example = "11988887777")
    private String telefone;

    @Schema(description = "ID da clínica de vínculo", example = "1")
    private Long idClinica;

    @Schema(description = "Nome da clínica de vínculo", example = "Clínica Vet Amigos")
    private String nomeClinica;
}
