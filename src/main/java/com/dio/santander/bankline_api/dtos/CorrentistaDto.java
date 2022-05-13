package com.dio.santander.bankline_api.dtos;

import com.dio.santander.bankline_api.models.ContaModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorrentistaDto {
    private String cpf;

    private String nome;

    private ContaModel conta;
}
