package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados retornados de um tutor")
public class TutorResponseDTO {

    @Schema(description = "ID do tutor", example = "1")
    private Long idTutor;

    @Schema(description = "Nome completo", example = "Maria da Silva")
    private String nome;

    @Schema(description = "E-mail", example = "maria@email.com")
    private String email;

    @Schema(description = "Telefone/WhatsApp", example = "11999999999")
    private String telefone;

    @Schema(description = "CPF sem formatação", example = "12345678901")
    private String cpf;

    @Schema(description = "Data de cadastro no sistema")
    private LocalDate dataCadastro;

    @Schema(description = "Cidade do tutor", example = "São Paulo")
    private String nomeCidade;

    @Schema(description = "Estado (UF)", example = "SP")
    private String estado;
}
