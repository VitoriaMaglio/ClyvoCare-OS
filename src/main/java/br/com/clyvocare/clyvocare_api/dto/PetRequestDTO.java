package br.com.clyvocare.clyvocare_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para cadastro ou atualização de pet")
public class PetRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100)
    @Schema(description = "Nome do animal", example = "Rex")
    private String nome;

    @Schema(description = "Data de nascimento", example = "2022-03-15")
    private LocalDate dataNascimento;

    @Pattern(regexp = "[MFI]", message = "Sexo deve ser M, F ou I")
    @Schema(description = "Sexo: M (Macho), F (Fêmea), I (Indeterminado)", example = "M")
    private String sexo;

    @DecimalMin(value = "0.001", message = "Peso deve ser maior que zero")
    @Schema(description = "Peso atual em kg", example = "12.500")
    private BigDecimal pesoAtual;

    @Size(max = 20)
    @Schema(description = "Número do microchip", example = "900123456789012")
    private String microchip;

    @Size(max = 500)
    @Schema(description = "URL da foto")
    private String fotoUrl;

    @NotNull(message = "Tutor é obrigatório")
    @Schema(description = "ID do tutor responsável", example = "1")
    private Long idTutor;

    @Schema(description = "ID da raça", example = "3")
    private Long idRaca;

    @NotNull(message = "Espécie é obrigatória")
    @Schema(description = "ID da espécie", example = "1")
    private Long idEspecie;
}
