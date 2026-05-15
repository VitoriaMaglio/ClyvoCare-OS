package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "VETERINARIOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Profissionais vinculados a uma clínica. O CRMV é único por veterinário")
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_veterinarios")
    @SequenceGenerator(name = "seq_veterinarios", sequenceName = "SEQ_VETERINARIOS", allocationSize = 1)
    @Column(name = "ID_VETERINARIO")
    @Schema(description = "Identificador único", example = "1")
    private Long idVeterinario;

    @Column(name = "NOME", nullable = false, length = 150)
    @Schema(description = "Nome completo", example = "Dr. João Souza")
    private String nome;

    @Column(name = "CRMV", nullable = false, unique = true, length = 20)
    @Schema(description = "Registro no conselho", example = "CRMV-SP 12345")
    private String crmv;

    @Column(name = "ESPECIALIDADE", length = 100)
    @Schema(description = "Área de atuação principal", example = "Cardiologia")
    private String especialidade;

    @Column(name = "EMAIL", length = 150)
    @Schema(description = "E-mail profissional", example = "joao@vetclinica.com")
    private String email;

    @Column(name = "TELEFONE", length = 20)
    @Schema(description = "Telefone de contato", example = "11988887777")
    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLINICA")
    @Schema(description = "Clínica de vínculo")
    private Clinica clinica;

    @OneToMany(mappedBy = "veterinario", fetch = FetchType.LAZY)
    private List<Consulta> consultas;
}
