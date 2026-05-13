package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MEDICAMENTOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Catálogo de medicamentos, desvinculado das prescrições para evitar redundância")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_medicamentos")
    @SequenceGenerator(name = "seq_medicamentos", sequenceName = "SEQ_MEDICAMENTOS", allocationSize = 1)
    @Column(name = "ID_MEDICAMENTO")
    @Schema(description = "Identificador único", example = "1")
    private Long idMedicamento;

    @Column(name = "NOME", nullable = false, length = 150)
    @Schema(description = "Nome comercial", example = "Amoxicilina 500mg")
    private String nome;

    @Column(name = "PRINCIPIO_ATIVO", length = 150)
    @Schema(description = "Substância ativa", example = "Amoxicilina")
    private String principioAtivo;

    @Column(name = "FABRICANTE", length = 100)
    @Schema(description = "Laboratório fabricante", example = "Eurofarma")
    private String fabricante;
}
