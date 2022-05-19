package com.dio.santander.bankline_api.services;

import com.dio.santander.bankline_api.models.MovimentacaoModel;
import com.dio.santander.bankline_api.repositories.CorrentistaRepository;
import com.dio.santander.bankline_api.repositories.MovimentacaoRepository;
import com.dio.santander.bankline_api.specifications.SpecificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovimentacaoService {


    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    CorrentistaRepository correntistaRepository;



    public List<MovimentacaoModel>findAll(){return movimentacaoRepository.findAll();}

    public Optional<MovimentacaoModel> findById(UUID movimentacaoId) {
        return movimentacaoRepository.findById(movimentacaoId);
    }

    public boolean existsByContaId(UUID contaId) {
       return  movimentacaoRepository.existsByContaId(contaId);
    }

    public Object save(MovimentacaoModel movimentacaoModel) {
        return movimentacaoRepository.save(movimentacaoModel);
    }

    public void delete(MovimentacaoModel movimentacaoModel) {
        movimentacaoRepository.delete(movimentacaoModel);
    }

    public Page<MovimentacaoModel> findAll(SpecificationTemplate.MoviSpec spec, Pageable pageable) {
        return movimentacaoRepository.findAll(spec, pageable);
    }
}
