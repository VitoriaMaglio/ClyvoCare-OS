package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "VACINAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Catálogo de vacinas por espécie, com intervalo padrão de reforço")
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vacinas")
    @SequenceGenerator(name = "seq_vacinas", sequenceName = "SEQ_VACINAS", allocationSize = 1)
    @Column(name = "ID_VACINA")
    @Schema(description = "Identificador único", example = "1")
    private Long idVacina;

    @Column(name = "NOME", nullable = false, length = 150)
    @Schema(description = "Nome comercial ou científico", example = "V10")
    private String nome;

    @Column(name = "FABRICANTE", length = 100)
    @Schema(description = "Laboratório fabricante", example = "MSD Saúde Animal")
    private String fabricante;

    @Column(name = "DOENCAS_PREVINE", length = 300)
    @Schema(description = "Doenças que a vacina cobre", example = "Cinomose, Parvovirose, Hepatite")
    private String doencasPrevine;

    @Column(name = "INTERVALO_REFORCO_DIAS", precision = 5)
    @Schema(description = "Intervalo padrão de reforço em dias", example = "365")
    private Integer intervaloReforcoDias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESPECIE", nullable = false)
    @Schema(description = "Espécie alvo")
    private Especie especie;
}
