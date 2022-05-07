package com.dio.santander.bankline_api.dtos;

import com.dio.santander.bankline_api.enums.MovimentacaoTipo;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter

public class MovimentacaoDto {
    private String descricao;
    private Double valor;
    private MovimentacaoTipo movimentacaoTipo;
    private UUID contaId;
}
