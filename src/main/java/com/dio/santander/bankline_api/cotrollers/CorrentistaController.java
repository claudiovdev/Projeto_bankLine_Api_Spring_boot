package com.dio.santander.bankline_api.cotrollers;

import com.dio.santander.bankline_api.dtos.CorrentistaDto;
import com.dio.santander.bankline_api.models.ContaModel;
import com.dio.santander.bankline_api.models.CorrentistaModel;
import com.dio.santander.bankline_api.services.CorrentistaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping()
public class CorrentistaController {

    @Autowired
    CorrentistaService correntistaService;

    @GetMapping("/correntistas")
    public ResponseEntity<Page<CorrentistaModel>> VisualizarCorrentistas(@PageableDefault(page = 0, size = 10, sort = "corentistaId", direction = Sort.Direction.ASC)
                                                                             Pageable pageable) {
        Page<CorrentistaModel> correntistaModelPage = correntistaService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(correntistaModelPage);
    }


    @PostMapping("/correntista")
    public ResponseEntity<Object> cadastrarCorrentista(@RequestBody CorrentistaDto correntistaDto){
        if(correntistaService.existsByCpf(correntistaDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Cpf já cadastrado");
        }
        var correntistaModel = new CorrentistaModel();
        var conta = new ContaModel();
        BeanUtils.copyProperties(correntistaDto,correntistaModel);
        conta.setSaldo(0.0);
        conta.setNumero(new Date().getTime());
        correntistaModel.setConta(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(correntistaService.save(correntistaModel));
    }

    @GetMapping("/correntista/{correntistaId}")
    public ResponseEntity<Object> buscandoUmCorrentista(@PathVariable(value = "correntistaId") UUID correntistaId){
        Optional<CorrentistaModel> correntistaModelOptional = correntistaService.findById(correntistaId);
        if(!correntistaModelOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Correntista não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(correntistaModelOptional.get());
    }

    @PutMapping("/correntista/{correntistaId}")
    ResponseEntity<Object> atualizarCorrentista(@PathVariable(value = "correntistaId")UUID correntistaId,
                                                @RequestBody CorrentistaDto correntistaDto){
        Optional<CorrentistaModel> correntistaModelOptional = correntistaService.findById(correntistaId);
        if(!correntistaModelOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Correntista não encontrado");
        }

        var correntistaModel = new CorrentistaModel();
        BeanUtils.copyProperties(correntistaDto,correntistaModel);
        correntistaModel.setConta(correntistaDto.getConta());
        return ResponseEntity.status(HttpStatus.CREATED).body(correntistaService.save(correntistaModel));
    }

    @DeleteMapping("/correntista/{correntistaId}")
    public ResponseEntity<Object> deletarCorrentista(@PathVariable(value = "correntistaId") UUID correntistaId){
        Optional<CorrentistaModel> correntistaModelOptional = correntistaService.findById(correntistaId);
        if(!correntistaModelOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Correntista não encontrado");
        }
        correntistaService.delete(correntistaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Correntista deletado com sucesso");
    }

}
