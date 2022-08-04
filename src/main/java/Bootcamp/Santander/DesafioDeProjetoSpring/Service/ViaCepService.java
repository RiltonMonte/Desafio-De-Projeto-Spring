package Bootcamp.Santander.DesafioDeProjetoSpring.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Bootcamp.Santander.DesafioDeProjetoSpring.Model.Endereco;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json")
    Endereco consultarCep(@PathVariable("cep") String cep);
    
}
