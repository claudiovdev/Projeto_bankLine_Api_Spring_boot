package com.dio.santander.bankline_api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "TB_CORRENTISTA")
public class CorrentistaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID corentistaId;

    private String cpf;

    private String nome;

    @Embedded
    private ContaModel conta;
}
