package com.dio.santander.bankline_api.services;

import com.dio.santander.bankline_api.dtos.CorrentistaDto;
import com.dio.santander.bankline_api.models.ContaModel;
import com.dio.santander.bankline_api.models.CorrentistaModel;
import com.dio.santander.bankline_api.repositories.CorrentistaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CorrentistaService {

    @Autowired
    CorrentistaRepository correntistaRepository;


    public List<CorrentistaModel> findAll() {
        return correntistaRepository.findAll();
    }

    public void save(CorrentistaDto correntistaDto) {

        var correntistaModel = new CorrentistaModel();
        var conta = new ContaModel();
        BeanUtils.copyProperties(correntistaDto, correntistaModel);
        conta.setSaldo(0.0);
        conta.setNumero(new Date().getTime());
        correntistaModel.setConta(conta);
        correntistaRepository.save(correntistaModel);
    }
}
