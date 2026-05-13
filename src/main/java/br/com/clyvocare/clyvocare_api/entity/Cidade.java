package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CIDADES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Municípios do Brasil, utilizada como referência geográfica para tutores, clínicas e alertas regionais")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cidades")
    @SequenceGenerator(name = "seq_cidades", sequenceName = "SEQ_CIDADES", allocationSize = 1)
    @Column(name = "ID_CIDADE")
    @Schema(description = "Identificador único", example = "1")
    private Long idCidade;

    @Column(name = "NOME", nullable = false, length = 100)
    @Schema(description = "Nome do município", example = "São Paulo")
    private String nome;

    @Column(name = "ESTADO", nullable = false, length = 2)
    @Schema(description = "Sigla do estado (UF)", example = "SP")
    private String estado;

    @Column(name = "REGIAO", nullable = false, length = 20)
    @Schema(description = "Região geográfica (Norte, Sul, etc.)", example = "Sudeste")
    private String regiao;
}
