package com.dio.santander.bankline_api.models;


import com.dio.santander.bankline_api.enums.MovimentacaoTipo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class MovimentacaoModel {
    private UUID movimentacaoId;
    private LocalDateTime dataHora;
    private String descricao;
    private Double valor;
    MovimentacaoTipo movimentacaoTipo;
}
