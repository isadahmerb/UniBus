package com.unibus.unibus_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "motorista")
public class Motorista {

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

    @Column(name = "numero_cnh", unique = true)
    private String numeroCnh;

    @Column(name = "vencimento_cnh")
    private java.time.LocalDate vencimentoCnh;

    private boolean ativo = true;
}