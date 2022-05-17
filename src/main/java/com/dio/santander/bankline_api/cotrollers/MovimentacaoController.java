package com.dio.santander.bankline_api.cotrollers;

import com.dio.santander.bankline_api.dtos.MovimentacaoDto;
import com.dio.santander.bankline_api.enums.MovimentacaoTipo;
import com.dio.santander.bankline_api.models.CorrentistaModel;
import com.dio.santander.bankline_api.models.MovimentacaoModel;
import com.dio.santander.bankline_api.services.CorrentistaService;
import com.dio.santander.bankline_api.services.MovimentacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping()
public class MovimentacaoController {

    @Autowired
    MovimentacaoService movimentacaoService;

    @Autowired
    CorrentistaService correntistaService;

    @GetMapping("/movimentacoes")
    public ResponseEntity<Page<MovimentacaoModel>> visualizarMovimentacoes(@PageableDefault(page = 0, size = 10, sort = "movimentacaoId", direction = Sort.Direction.ASC)
                                                                               Pageable pageable){
        Page<MovimentacaoModel> movimentacaoModelPage = movimentacaoService.findAll(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoModelPage);
    }

    @GetMapping("movimentacao/{movimentacaoId}")
    public ResponseEntity<Object> buscandoUmaMovimentacao(@PathVariable(value = "movimentacaoId")UUID movimentacaoId){
        Optional<MovimentacaoModel> movimentacaoModelOptional = movimentacaoService.findById(movimentacaoId);
        if(!movimentacaoModelOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Movimentação não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoModelOptional.get());
    }

    @PostMapping("/movimentacao")
    public ResponseEntity<Object> criarMovimentacao(@RequestBody MovimentacaoDto movimentacaoDto){
        if(!movimentacaoService.existsByContaId(movimentacaoDto.getContaId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: conta não registrada");
        }

        var movimentacaoModel = new MovimentacaoModel();
        //Double valor = movimentacaoDto.getMovimentacaoTipo()==MovimentacaoTipo.RECEITA ? movimentacaoDto.getValor() : movimentacaoDto.getValor() *-1;
        Double valor = movimentacaoDto.getValor();

        if(movimentacaoDto.getMovimentacaoTipo() == MovimentacaoTipo.DESPESA){
            valor = valor * -1;
        }


        movimentacaoModel.setDataHora(LocalDateTime.now());
        movimentacaoModel.setDescricao(movimentacaoDto.getDescricao());
        movimentacaoModel.setContaId(movimentacaoDto.getContaId());
        movimentacaoModel.setMovimentacaoTipo(movimentacaoDto.getMovimentacaoTipo());
        movimentacaoModel.setValor(movimentacaoDto.getValor());

        CorrentistaModel correntista = correntistaService.findById(movimentacaoDto.getContaId()).orElse(null);
        if(correntista != null){
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
            correntistaService.save(correntista);
        }

        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.save(movimentacaoModel)) ;
    }

    @PutMapping("movimentacao/{movimentacaoId}")
    public ResponseEntity<Object> atualizarMovimentacao(@PathVariable(value = "movimentacaoId") UUID movimentacaoId,
                                                        @RequestBody MovimentacaoDto movimentacaoDto){
        Optional<MovimentacaoModel> movimentacaoModelOptional = movimentacaoService.findById(movimentacaoId);
        if(!movimentacaoModelOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Movimentação não encontrada");
        }

        Double valor = movimentacaoDto.getValor();
        if(movimentacaoDto.getMovimentacaoTipo() == MovimentacaoTipo.DESPESA){
            valor = valor * -1;
        }
        var movimentacaoModel = new MovimentacaoModel();
        BeanUtils.copyProperties(movimentacaoDto,movimentacaoModel);
        movimentacaoModel.setDataHora(LocalDateTime.now());

        CorrentistaModel correntista = correntistaService.findById(movimentacaoDto.getContaId()).orElse(null);
        if(correntista != null){
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
            correntistaService.save(correntista);
        }

        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.save(movimentacaoModel)) ;
    }

    @DeleteMapping("movimentacao/{movimentacaoId}")
    public ResponseEntity<Object> deletarMovimentacao(@PathVariable(value = "movimentacaoId") UUID movimentacaoId){
        Optional<MovimentacaoModel>  movimentacaoModelOptional = movimentacaoService.findById(movimentacaoId);
        if(!movimentacaoModelOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Movimentação não encontrado");
        }
        movimentacaoService.delete(movimentacaoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Movimentação deletado com sucesso");
    }

}



