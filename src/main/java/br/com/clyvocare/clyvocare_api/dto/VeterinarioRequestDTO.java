package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para cadastro ou atualização de veterinário")
public class VeterinarioRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 150)
    @Schema(description = "Nome completo", example = "Dr. João Souza")
    private String nome;

    @NotBlank(message = "CRMV é obrigatório")
    @Size(max = 20)
    @Schema(description = "Registro no conselho", example = "CRMV-SP 12345")
    private String crmv;

    @Size(max = 100)
    @Schema(description = "Área de atuação principal", example = "Cardiologia")
    private String especialidade;

    @Email(message = "E-mail inválido")
    @Size(max = 150)
    @Schema(description = "E-mail profissional", example = "joao@vetclinica.com")
    private String email;

    @Size(max = 20)
    @Schema(description = "Telefone de contato", example = "11988887777")
    private String telefone;

    @Schema(description = "ID da clínica de vínculo", example = "1")
    private Long idClinica;
}
