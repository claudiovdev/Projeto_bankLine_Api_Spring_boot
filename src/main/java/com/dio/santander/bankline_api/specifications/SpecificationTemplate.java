package com.dio.santander.bankline_api.specifications;

import com.dio.santander.bankline_api.models.CorrentistaModel;
import com.dio.santander.bankline_api.models.MovimentacaoModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {
    // Abaixo eu defino que meu filtro pode obter duas pesquisar.
    // Tanto de nome quanto de cpf.
    // Defino no path o nome do parametro que vou buscar e o Like me diz que se tiver o nome parecido ele vai retornar todos que tem aquele nome
    @And({
            @Spec(path = "nome", spec = Like.class),
            @Spec(path = "cpf", spec = Like.class)


    })
    public interface UserSpec extends Specification<CorrentistaModel> {}

    @Spec(path = "movimentacaoTipo", spec = Equal.class)
    public interface MoviSpec extends Specification<MovimentacaoModel> {}

}
