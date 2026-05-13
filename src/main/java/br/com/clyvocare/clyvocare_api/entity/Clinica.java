package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "CLINICAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Clínicas veterinárias que assinam a plataforma e acessam o dashboard clínico")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_clinicas")
    @SequenceGenerator(name = "seq_clinicas", sequenceName = "SEQ_CLINICAS", allocationSize = 1)
    @Column(name = "ID_CLINICA")
    @Schema(description = "Identificador único", example = "1")
    private Long idClinica;

    @Column(name = "NOME", nullable = false, length = 150)
    @Schema(description = "Razão social ou nome fantasia", example = "Clínica Vet Amigos")
    private String nome;

    @Column(name = "CNPJ", nullable = false, unique = true, length = 14)
    @Schema(description = "CNPJ sem formatação", example = "12345678000190")
    private String cnpj;

    @Column(name = "TELEFONE", length = 20)
    @Schema(description = "Telefone de contato", example = "1133334444")
    private String telefone;

    @Column(name = "EMAIL", length = 150)
    @Schema(description = "E-mail institucional", example = "contato@vetamigos.com.br")
    private String email;

    @Column(name = "ENDERECO", length = 300)
    @Schema(description = "Endereço completo")
    private String endereco;

    @Column(name = "PLANO_ASSINATURA", length = 20)
    @Schema(description = "Nível do plano (BASICO/PRO/ENTERPRISE)", example = "PRO")
    private String planoAssinatura;

    @Column(name = "DATA_ASSINATURA")
    @Schema(description = "Data de início da assinatura")
    private LocalDate dataAssinatura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CIDADE")
    @Schema(description = "Localização da clínica")
    private Cidade cidade;
}
