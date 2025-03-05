package br.com.exemplo.apiclientes.service;

import br.com.exemplo.apiclientes.model.Cliente;
import br.com.exemplo.apiclientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).get();
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente clienteAtual = clienteRepository.findById(id).get();

        if(clienteAtual != null) {
            clienteAtual.setNome(cliente.getNome());
            clienteAtual.setEmail(cliente.getEmail());
            clienteAtual.setTelefone(cliente.getTelefone());

            return clienteRepository.save(clienteAtual);
        }

        return null;
    }

    public Cliente deletar(Long id) {
        Cliente clienteAtual = clienteRepository.findById(id).get();

        if (clienteAtual != null) {
            clienteRepository.delete(clienteAtual);
            return clienteAtual;
        }

        return null;
    }


}
