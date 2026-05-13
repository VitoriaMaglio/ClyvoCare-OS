package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PETS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidade central do sistema. Cada animal pertence a um tutor, tem raça e espécie definidas")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pets")
    @SequenceGenerator(name = "seq_pets", sequenceName = "SEQ_PETS", allocationSize = 1)
    @Column(name = "ID_PET")
    @Schema(description = "Identificador único", example = "1")
    private Long idPet;

    @Column(name = "NOME", nullable = false, length = 100)
    @Schema(description = "Nome do animal", example = "Rex")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    @Schema(description = "Data de nascimento estimada ou real")
    private LocalDate dataNascimento;

    @Column(name = "SEXO", length = 1)
    @Schema(description = "Macho, Fêmea ou Indeterminado (M/F/I)", example = "M")
    private String sexo;

    @Column(name = "PESO_ATUAL", precision = 6, scale = 3)
    @Schema(description = "Peso em kg na última atualização", example = "12.500")
    private BigDecimal pesoAtual;

    @Column(name = "MICROCHIP", unique = true, length = 20)
    @Schema(description = "Número do microchip", example = "900123456789012")
    private String microchip;

    @Column(name = "FOTO_URL", length = 500)
    @Schema(description = "URL da foto no storage")
    private String fotoUrl;

    @Column(name = "DATA_CADASTRO", nullable = false)
    @Schema(description = "Data de registro no sistema")
    private LocalDate dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TUTOR", nullable = false)
    @Schema(description = "Dono do animal")
    private Tutor tutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RACA")
    @Schema(description = "Raça do animal")
    private Raca raca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESPECIE", nullable = false)
    @Schema(description = "Espécie do animal")
    private Especie especie;
}
