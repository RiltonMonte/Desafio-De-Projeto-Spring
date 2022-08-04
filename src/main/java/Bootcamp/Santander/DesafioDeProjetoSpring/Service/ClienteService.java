package Bootcamp.Santander.DesafioDeProjetoSpring.Service;

import Bootcamp.Santander.DesafioDeProjetoSpring.Model.Cliente;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Cliente cliente, Long id);

    void deletar(Long id);
    
}
