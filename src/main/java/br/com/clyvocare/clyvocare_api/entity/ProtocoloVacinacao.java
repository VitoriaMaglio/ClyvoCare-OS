package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROTOCOLOS_VACINACAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Tabela associativa que define quais vacinas devem ser aplicadas em cada fase de vida")
public class ProtocoloVacinacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_protocolos_vac")
    @SequenceGenerator(name = "seq_protocolos_vac", sequenceName = "SEQ_PROTOCOLOS_VACINACAO", allocationSize = 1)
    @Column(name = "ID_PROTOCOLO")
    @Schema(description = "Identificador único", example = "1")
    private Long idProtocolo;

    @Column(name = "OBRIGATORIA", nullable = false, length = 1)
    @Schema(description = "Indica vacina obrigatória (S/N)", example = "S")
    private String obrigatoria;

    @Column(name = "IDADE_APLICACAO_DIAS", precision = 5)
    @Schema(description = "Idade ideal de aplicação em dias", example = "60")
    private Integer idadeAplicacaoDias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VACINA", nullable = false)
    @Schema(description = "Vacina do protocolo")
    private Vacina vacina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FASE", nullable = false)
    @Schema(description = "Fase de vida de aplicação")
    private FaseVida faseVida;
}
