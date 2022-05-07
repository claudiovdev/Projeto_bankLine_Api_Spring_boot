package com.dio.santander.bankline_api.services;

import com.dio.santander.bankline_api.dtos.CorrentistaDto;
import com.dio.santander.bankline_api.models.ContaModel;
import com.dio.santander.bankline_api.models.CorrentistaModel;
import com.dio.santander.bankline_api.repositories.CorrentistaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CorrentistaService {

    @Autowired
    CorrentistaRepository correntistaRepository;


    public ResponseEntity<List<CorrentistaModel>>findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(correntistaRepository.findAll());
    }
    
    public ResponseEntity<Object>save(CorrentistaDto correntistaDto) {

        if(correntistaRepository.existsByCpf(correntistaDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: este cpj já está cadastrado");
        }

        var correntistaModel = new CorrentistaModel();
        var conta = new ContaModel();
        BeanUtils.copyProperties(correntistaDto, correntistaModel);
        conta.setSaldo(0.0);
        conta.setNumero(new Date().getTime());
        correntistaModel.setConta(conta);
       return ResponseEntity.status(HttpStatus.CREATED).body(correntistaRepository.save(correntistaModel));
    }

    public ResponseEntity<Object> findById(UUID correntistaId) {
        Optional<CorrentistaModel> correntistaModelOptional = correntistaRepository.findById(correntistaId);
        if(!correntistaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: correntista não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(correntistaModelOptional.get());
    }
}
