package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "HISTORICO_PESO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Histórico de peso do animal ao longo do tempo para acompanhamento nutricional")
public class HistoricoPeso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico_peso")
    @SequenceGenerator(name = "seq_historico_peso", sequenceName = "SEQ_HISTORICO_PESO", allocationSize = 1)
    @Column(name = "ID_HISTORICO")
    @Schema(description = "Identificador único", example = "1")
    private Long idHistorico;

    @Column(name = "DATA_REGISTRO", nullable = false)
    @Schema(description = "Data da pesagem")
    private LocalDate dataRegistro;

    @Column(name = "PESO", nullable = false, precision = 6, scale = 3)
    @Schema(description = "Peso em kg", example = "12.500")
    private BigDecimal peso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PET", nullable = false)
    @Schema(description = "Animal pesado")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CONSULTA")
    @Schema(description = "Consulta associada (se aplicável)")
    private Consulta consulta;
}
