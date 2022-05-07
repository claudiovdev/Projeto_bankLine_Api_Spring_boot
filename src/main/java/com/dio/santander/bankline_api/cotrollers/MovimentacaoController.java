package com.dio.santander.bankline_api.cotrollers;

import com.dio.santander.bankline_api.dtos.MovimentacaoDto;

import com.dio.santander.bankline_api.models.MovimentacaoModel;
import com.dio.santander.bankline_api.services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class MovimentacaoController {

    @Autowired
    MovimentacaoService movimentacaoService;

    @GetMapping("/movimentacoes")
    public ResponseEntity<List<MovimentacaoModel>> visualizarMovimentacoes(){
        return movimentacaoService.findAll();
    }

    @PostMapping("/movimentacao")
    public ResponseEntity<Object> criarMovimentacao(@RequestBody MovimentacaoDto movimentacaoDto){
        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.save(movimentacaoDto));
    }
}
