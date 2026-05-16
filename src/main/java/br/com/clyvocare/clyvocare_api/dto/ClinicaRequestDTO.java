package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para cadastro ou atualização de clínica veterinária")
public class ClinicaRequestDTO {

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150)
        @Schema(description = "Razão social ou nome fantasia", example = "Clínica Vet Amigos")
        private String nome;

        @NotBlank(message = "CNPJ é obrigatório")
        @Size(min = 14, max = 14, message = "CNPJ deve ter 14 dígitos")
        @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter apenas números")
        @Schema(description = "CNPJ sem formatação", example = "12345678000190")
        private String cnpj;

        @Size(max = 20)
        @Schema(description = "Telefone de contato", example = "1133334444")
        private String telefone;

        @Email(message = "E-mail inválido")
        @Size(max = 150)
        @Schema(description = "E-mail institucional", example = "contato@vetamigos.com.br")
        private String email;

        @Size(max = 300)
        @Schema(description = "Endereço completo", example = "Rua das Flores, 100 - São Paulo/SP")
        private String endereco;

        @Pattern(regexp = "BASICO|PRO|ENTERPRISE", message = "Plano deve ser BASICO, PRO ou ENTERPRISE")
        @Schema(description = "Plano de assinatura", example = "PRO")
        private String planoAssinatura;

        @Schema(description = "Data de início da assinatura", example = "2025-01-01")
        private LocalDate dataAssinatura;

        @Schema(description = "ID da cidade", example = "1")
        private Long idCidade;
    }

