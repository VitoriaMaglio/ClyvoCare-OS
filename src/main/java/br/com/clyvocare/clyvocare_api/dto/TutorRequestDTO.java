package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para cadastro ou atualização de tutor")
public class TutorRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
    @Schema(description = "Nome completo", example = "Maria da Silva")
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(max = 150)
    @Schema(description = "E-mail de acesso", example = "maria@email.com")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    @Size(max = 20)
    @Schema(description = "Telefone/WhatsApp", example = "11999999999")
    private String telefone;

    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas números")
    @Schema(description = "CPF sem formatação", example = "12345678901")
    private String cpf;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Schema(description = "Senha de acesso (será armazenada como hash)")
    private String senha;

    @Schema(description = "ID da cidade do tutor", example = "1")
    private Long idCidade;
}
