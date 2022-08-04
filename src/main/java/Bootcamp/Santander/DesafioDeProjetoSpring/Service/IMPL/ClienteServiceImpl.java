package Bootcamp.Santander.DesafioDeProjetoSpring.Service.IMPL;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Bootcamp.Santander.DesafioDeProjetoSpring.Model.Cliente;
import Bootcamp.Santander.DesafioDeProjetoSpring.Model.ClienteRepository;
import Bootcamp.Santander.DesafioDeProjetoSpring.Model.Endereco;
import Bootcamp.Santander.DesafioDeProjetoSpring.Model.EnderecoRepository;
import Bootcamp.Santander.DesafioDeProjetoSpring.Service.ClienteService;
import Bootcamp.Santander.DesafioDeProjetoSpring.Service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
       Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarCliente(cliente);
    }

    @Override
    public void atualizar(Cliente cliente, Long id) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            salvarCliente(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
    
    private void salvarCliente(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(()->{
           Endereco novoEndereco = viaCepService.consultarCep(cep);
           enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

}
