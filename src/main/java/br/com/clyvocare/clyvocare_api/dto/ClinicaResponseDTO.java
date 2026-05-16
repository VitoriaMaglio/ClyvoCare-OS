package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados retornados de uma clínica veterinária")
public class ClinicaResponseDTO {

    @Schema(description = "ID da clínica", example = "1")
    private Long idClinica;

    @Schema(description = "Razão social ou nome fantasia", example = "Clínica Vet Amigos")
    private String nome;

    @Schema(description = "CNPJ sem formatação", example = "12345678000190")
    private String cnpj;

    @Schema(description = "Telefone de contato", example = "1133334444")
    private String telefone;

    @Schema(description = "E-mail institucional", example = "contato@vetamigos.com.br")
    private String email;

    @Schema(description = "Endereço completo")
    private String endereco;

    @Schema(description = "Plano de assinatura", example = "PRO")
    private String planoAssinatura;

    @Schema(description = "Data de início da assinatura")
    private LocalDate dataAssinatura;

    @Schema(description = "Cidade da clínica", example = "São Paulo")
    private String nomeCidade;

    @Schema(description = "Estado (UF)", example = "SP")
    private String estado;
}
