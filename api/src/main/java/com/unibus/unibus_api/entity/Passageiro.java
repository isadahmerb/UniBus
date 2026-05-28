package com.unibus.unibus_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "passageiro")
public class Passageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String telefone;

    @Column(name = "endereco_padrao")
    private String enderecoPadrao;

    @ManyToOne
    @JoinColumn(name = "rota_id")
    private Rota rota;

    private boolean ativo = true;
}