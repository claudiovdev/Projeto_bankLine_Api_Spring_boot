package com.dio.santander.bankline_api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter

@Embeddable
public class ContaModel {
    @Column(name = "conta_numero")
    private Long numero;
    @Column(name = "conta_saldo")
    private Double saldo;
}
