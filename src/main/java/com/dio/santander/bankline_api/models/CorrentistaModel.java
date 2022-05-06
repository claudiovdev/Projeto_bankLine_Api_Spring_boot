package com.dio.santander.bankline_api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "TB_CORRENTISTA")
public class CorrentistaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID corentistaId;

    private String cpf;

    private String nome;

    @Embedded
    private ContaModel conta;
}
