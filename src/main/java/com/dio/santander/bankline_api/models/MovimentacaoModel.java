package com.dio.santander.bankline_api.models;


import com.dio.santander.bankline_api.enums.MovimentacaoTipo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "TB_MOVIMENTACAO")
public class MovimentacaoModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID movimentacaoId;
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    private String descricao;
    private Double valor;

    @Enumerated(EnumType.STRING)
    MovimentacaoTipo movimentacaoTipo;

    @Column(name = "conta_id")
    private UUID contaId;
}
