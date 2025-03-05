package br.com.exemplo.apipedido.controller;

import br.com.exemplo.apipedido.model.Pedido;
import br.com.exemplo.apipedido.repository.PedidoRepository;
import br.com.exemplo.apipedido.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        try{
            return ResponseEntity.ok(pedidoService.listar());
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id){
        try{
            Pedido pedido = pedidoService.buscar(id);
            return ResponseEntity.ok(pedido);
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pedido> adicionar(@RequestBody Pedido pedido){
        try{
            return ResponseEntity.ok(pedidoService.salvar(pedido));
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido){
        try{
            pedido.setId(id);
            return ResponseEntity.ok(pedidoService.atualizar(pedido));
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        try{
            pedidoService.excluir(id);
            return ResponseEntity.ok().build();
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
