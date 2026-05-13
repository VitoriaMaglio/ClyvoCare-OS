package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "VACINACOES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Histórico de vacinas aplicadas ao animal")
public class Vacinacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vacinacoes")
    @SequenceGenerator(name = "seq_vacinacoes", sequenceName = "SEQ_VACINACOES", allocationSize = 1)
    @Column(name = "ID_VACINACAO")
    @Schema(description = "Identificador único", example = "1")
    private Long idVacinacao;

    @Column(name = "DATA_APLICACAO", nullable = false)
    @Schema(description = "Data efetiva da aplicação")
    private LocalDate dataAplicacao;

    @Column(name = "LOTE", length = 50)
    @Schema(description = "Número do lote da vacina", example = "L2024A001")
    private String lote;

    @Column(name = "PROXIMA_DOSE")
    @Schema(description = "Data calculada para próxima dose")
    private LocalDate proximaDose;

    @Column(name = "VALIDADE")
    @Schema(description = "Validade da vacina aplicada")
    private LocalDate validade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PET", nullable = false)
    @Schema(description = "Animal vacinado")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VACINA", nullable = false)
    @Schema(description = "Vacina aplicada")
    private Vacina vacina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CONSULTA")
    @Schema(description = "Consulta associada (opcional)")
    private Consulta consulta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VETERINARIO")
    @Schema(description = "Responsável pela aplicação")
    private Veterinario veterinario;
}
