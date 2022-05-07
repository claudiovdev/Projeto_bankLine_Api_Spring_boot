package com.dio.santander.bankline_api.services;

import com.dio.santander.bankline_api.dtos.MovimentacaoDto;
import com.dio.santander.bankline_api.enums.MovimentacaoTipo;
import com.dio.santander.bankline_api.models.CorrentistaModel;
import com.dio.santander.bankline_api.models.MovimentacaoModel;
import com.dio.santander.bankline_api.repositories.CorrentistaRepository;
import com.dio.santander.bankline_api.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {


    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    CorrentistaRepository correntistaRepository;

    //public List<MovimentacaoModel> findAll() {return movimentacaoRepository.findAll();}

    public ResponseEntity<List<MovimentacaoModel>>findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoRepository.findAll());
    }

    public ResponseEntity<Object> save(MovimentacaoDto movimentacaoDto){

        if(!movimentacaoRepository.existsByContaId(movimentacaoDto.getContaId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: conta não registrada");
        }

        var movimentacaoModel = new MovimentacaoModel();
        //Double valor = movimentacaoDto.getMovimentacaoTipo()==MovimentacaoTipo.RECEITA ? movimentacaoDto.getValor() : movimentacaoDto.getValor() *-1;
        Double valor = movimentacaoDto.getValor();

        if(movimentacaoDto.getMovimentacaoTipo() == MovimentacaoTipo.DESPESA){
            valor = valor * -1;
        }


        movimentacaoModel.setDataHora(LocalDateTime.now());
        movimentacaoModel.setDescricao(movimentacaoDto.getDescricao());
        movimentacaoModel.setContaId(movimentacaoDto.getContaId());
        movimentacaoModel.setMovimentacaoTipo(movimentacaoDto.getMovimentacaoTipo());
        movimentacaoModel.setValor(movimentacaoDto.getValor());

        CorrentistaModel correntista = correntistaRepository.findById(movimentacaoDto.getContaId()).orElse(null);
        if(correntista != null){
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
            correntistaRepository.save(correntista);
        }

         return ResponseEntity.status(HttpStatus.OK).body(movimentacaoRepository.save(movimentacaoModel)) ;
    }


}
