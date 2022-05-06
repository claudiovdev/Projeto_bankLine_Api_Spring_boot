package com.dio.santander.bankline_api.cotrollers;

import com.dio.santander.bankline_api.dtos.CorrentistaDto;
import com.dio.santander.bankline_api.models.ContaModel;
import com.dio.santander.bankline_api.repositories.CorrentistaRepository;
import com.dio.santander.bankline_api.models.CorrentistaModel;
import com.dio.santander.bankline_api.services.CorrentistaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;



@RestController
@RequestMapping()
public class CorrentistaController {

    @Autowired
    CorrentistaService correntistaService;

    @GetMapping("/correntistas")
    public List<CorrentistaModel> VisualizarCorrentistas(){
        return correntistaService.findAll();
    }

    @PostMapping("/correntista")
    public  void CriarCorrentista(@RequestBody CorrentistaDto correntistaDto){
        correntistaService.save(correntistaDto);
    }
}
