package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TUTORES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Proprietários/responsáveis pelos animais. Um tutor pode ter múltiplos pets")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tutores")
    @SequenceGenerator(name = "seq_tutores", sequenceName = "SEQ_TUTORES", allocationSize = 1)
    @Column(name = "ID_TUTOR")
    @Schema(description = "Identificador único", example = "1")
    private Long idTutor;

    @Column(name = "NOME", nullable = false, length = 150)
    @Schema(description = "Nome completo", example = "Maria da Silva")
    private String nome;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 150)
    @Schema(description = "E-mail de acesso", example = "maria@email.com")
    private String email;

    @Column(name = "TELEFONE", nullable = false, length = 20)
    @Schema(description = "Telefone/WhatsApp", example = "11999999999")
    private String telefone;

    @Column(name = "CPF", nullable = false, unique = true, length = 11)
    @Schema(description = "CPF sem formatação", example = "12345678901")
    private String cpf;

    @Column(name = "SENHA_HASH", nullable = false, length = 255)
    @Schema(description = "Hash bcrypt da senha")
    private String senhaHash;

    @Column(name = "DATA_CADASTRO", nullable = false)
    @Schema(description = "Data de registro no sistema")
    private LocalDate dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CIDADE")
    @Schema(description = "Cidade do tutor")
    private Cidade cidade;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    private List<Pet> pets;
}
