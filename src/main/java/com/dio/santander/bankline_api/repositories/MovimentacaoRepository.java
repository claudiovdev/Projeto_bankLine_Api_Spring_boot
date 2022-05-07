package com.dio.santander.bankline_api.repositories;

import com.dio.santander.bankline_api.models.MovimentacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoModel, UUID> {
    boolean existsByContaId(UUID contaId);
}
