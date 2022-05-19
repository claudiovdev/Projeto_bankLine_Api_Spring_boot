package com.dio.santander.bankline_api.repositories;

import com.dio.santander.bankline_api.models.CorrentistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CorrentistaRepository extends JpaRepository<CorrentistaModel, UUID>, JpaSpecificationExecutor<CorrentistaModel> {

    boolean existsByCpf(String cpf);

}
