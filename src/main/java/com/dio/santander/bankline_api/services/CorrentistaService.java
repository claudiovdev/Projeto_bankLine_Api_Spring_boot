package com.dio.santander.bankline_api.services;

import com.dio.santander.bankline_api.models.CorrentistaModel;
import com.dio.santander.bankline_api.repositories.CorrentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CorrentistaService {

    @Autowired
    CorrentistaRepository correntistaRepository;


    public ResponseEntity<List<CorrentistaModel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(correntistaRepository.findAll());
    }


    public CorrentistaModel save(CorrentistaModel correntistaModel) {
        return correntistaRepository.save(correntistaModel);
    }

    public boolean existsByCpf(String cpf) {
        return correntistaRepository.existsByCpf(cpf);
    }

    public Optional<CorrentistaModel> findById(UUID correntistaId) {
       return correntistaRepository.findById(correntistaId);
    }


    public void delete(CorrentistaModel correntistaModel) {
        correntistaRepository.delete(correntistaModel);
    }

    public Page<CorrentistaModel> findAll(Pageable pageable) {
        return correntistaRepository.findAll(pageable);
    }
}
