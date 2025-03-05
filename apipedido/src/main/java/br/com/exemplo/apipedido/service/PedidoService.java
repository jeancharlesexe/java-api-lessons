package br.com.exemplo.apipedido.service;

import br.com.exemplo.apipedido.model.Pedido;
import br.com.exemplo.apipedido.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscar(Long id) {
        return pedidoRepository.findById(id).orElseThrow(
           () -> new EntityNotFoundException("Não encontrado")
        );
    }

    public Pedido salvar(Pedido pedido) {
        pedido.setDataPedido(LocalDate.now());
        return pedidoRepository.save(pedido);
    }

    public void excluir(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido atualizar(Pedido pedido) {
        pedido.setDataPedido(LocalDate.now());
        return pedidoRepository.save(pedido);
    }
}
