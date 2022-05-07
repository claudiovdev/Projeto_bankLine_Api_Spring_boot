package com.dio.santander.bankline_api.cotrollers;

import com.dio.santander.bankline_api.dtos.MovimentacaoDto;

import com.dio.santander.bankline_api.models.MovimentacaoModel;
import com.dio.santander.bankline_api.services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class MovimentacaoController {

    @Autowired
    MovimentacaoService movimentacaoService;

    @GetMapping("/movimentacoes")
    public List<MovimentacaoModel> visualizarMovimentacoes(){
        return movimentacaoService.findAll();
    }

    @PostMapping("/movimentacao")
    public void criarMovimentacao(@RequestBody MovimentacaoDto movimentacaoDto){
        movimentacaoService.save(movimentacaoDto);
    }
}
