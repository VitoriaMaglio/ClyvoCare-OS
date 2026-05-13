package br.com.clyvocare.clyvocare_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "LEMBRETES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Lembretes automáticos enviados via WhatsApp, push ou e-mail para tutores")
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lembretes")
    @SequenceGenerator(name = "seq_lembretes", sequenceName = "SEQ_LEMBRETES", allocationSize = 1)
    @Column(name = "ID_LEMBRETE")
    @Schema(description = "Identificador único", example = "1")
    private Long idLembrete;

    @Column(name = "TIPO", length = 30)
    @Schema(description = "Categoria do lembrete (VACINA/CONSULTA/EXAME/MEDICAMENTO)", example = "VACINA")
    private String tipo;

    @Column(name = "DATA_EVENTO", nullable = false)
    @Schema(description = "Data do evento a ser lembrado")
    private LocalDate dataEvento;

    @Column(name = "MENSAGEM", nullable = false, length = 500)
    @Schema(description = "Texto do lembrete")
    private String mensagem;

    @Column(name = "STATUS", length = 15)
    @Schema(description = "Estado do envio (PENDENTE/ENVIADO/CONFIRMADO)", example = "PENDENTE")
    private String status;

    @Column(name = "CANAL", length = 15)
    @Schema(description = "Canal de envio (WHATSAPP/PUSH/EMAIL)", example = "WHATSAPP")
    private String canal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PET", nullable = false)
    @Schema(description = "Animal referenciado")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TUTOR", nullable = false)
    @Schema(description = "Destinatário do lembrete")
    private Tutor tutor;
}
