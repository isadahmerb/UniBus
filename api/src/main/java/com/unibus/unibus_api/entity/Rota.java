package com.unibus.unibus_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rota")
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(name = "horario_saida")
    private String horarioSaida;

    @Column(name = "horario_retorno")
    private String horarioRetorno;

    private boolean ativa = true;
}