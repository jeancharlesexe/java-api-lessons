package br.com.exemplo.apilivro.controller;

import br.com.exemplo.apilivro.model.Livro;
import br.com.exemplo.apilivro.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @PostMapping
    private ResponseEntity<Livro> cadastrarLivro(@RequestBody Livro livro) {
        Livro livroCadastrado = livroService.cadastrar(livro);

        if (livroCadastrado != null) {
            return ResponseEntity.ok(livroCadastrado);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        List<Livro> livros = livroService.listar();

        if(livros != null) {
            return ResponseEntity.ok(livros);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscar(id);

        if(livro.isPresent()) {
            return ResponseEntity.ok(livro.get());
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livro) {
        Optional<Livro> livroEncontrado = livroService.buscar(id);

        if(livroEncontrado.isPresent()) {
            return ResponseEntity.ok(livroService.atualizar(id, livro));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Livro> livroEncontrado = livroService.buscar(id);

        if(livroEncontrado.isPresent()) {
            livroService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.noContent().build();
    }
}
