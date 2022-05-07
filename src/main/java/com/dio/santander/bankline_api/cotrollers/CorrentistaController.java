package com.dio.santander.bankline_api.cotrollers;

import com.dio.santander.bankline_api.dtos.CorrentistaDto;
import com.dio.santander.bankline_api.models.CorrentistaModel;
import com.dio.santander.bankline_api.services.CorrentistaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;



@RestController
@RequestMapping()
public class CorrentistaController {

    @Autowired
    CorrentistaService correntistaService;

    @GetMapping("/correntistas")
    public ResponseEntity<List<CorrentistaModel>> VisualizarCorrentistas(){
        return correntistaService.findAll();
    }


    @PostMapping("/correntista")
    public void CriarCorrentista(@RequestBody CorrentistaDto correntistaDto){
        correntistaService.save(correntistaDto);
    }
}
