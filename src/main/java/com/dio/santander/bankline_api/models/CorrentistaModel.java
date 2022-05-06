package com.dio.santander.bankline_api.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CorrentistaModel {

    private UUID corentistaId;
    private String cpf;
    private String nome;

    private ContaModel conta;
}
