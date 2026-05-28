package com.unibus.unibus_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "confirmacao_presenca")
public class ConfirmacaoPresenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viagem_id", nullable = false)
    private Viagem viagem;

    @ManyToOne
    @JoinColumn(name = "passageiro_id", nullable = false)
    private Passageiro passageiro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPresenca status = StatusPresenca.PENDENTE;

    @Column(name = "endereco_do_dia")
    private String enderecoDoDia;

    public enum StatusPresenca {
        PENDENTE, CONFIRMADO, CANCELADO
    }
}