package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados retornados de um pet")
public class PetResponseDTO {

    @Schema(description = "ID do pet", example = "1")
    private Long idPet;

    @Schema(description = "Nome do animal", example = "Rex")
    private String nome;

    @Schema(description = "Data de nascimento")
    private LocalDate dataNascimento;

    @Schema(description = "Idade em meses calculada", example = "18")
    private Integer idadeMeses;

    @Schema(description = "Fase de vida atual", example = "Adulto")
    private String faseVida;

    @Schema(description = "Sexo (M/F/I)", example = "M")
    private String sexo;

    @Schema(description = "Peso atual em kg", example = "12.500")
    private BigDecimal pesoAtual;

    @Schema(description = "Microchip", example = "900123456789012")
    private String microchip;

    @Schema(description = "URL da foto")
    private String fotoUrl;

    @Schema(description = "Data de cadastro")
    private LocalDate dataCadastro;

    @Schema(description = "Nome do tutor", example = "Maria da Silva")
    private String nomeTutor;

    @Schema(description = "ID do tutor", example = "1")
    private Long idTutor;

    @Schema(description = "Espécie", example = "Cão")
    private String especie;

    @Schema(description = "Raça", example = "Golden Retriever")
    private String raca;

    @Schema(description = "Nível de risco atual", example = "MEDIO")
    private String nivelRisco;
}
