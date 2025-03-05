package br.com.exemplo.apilivro.service;

import br.com.exemplo.apilivro.model.Livro;
import br.com.exemplo.apilivro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public Livro cadastrar(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> listar() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscar(Long id) {
        return livroRepository.findById(id);
    }

    public Livro atualizar(Long id, Livro livro) {
        Livro livroAtualizado = livroRepository.findById(id).get();

        livroAtualizado.setTitulo(livro.getTitulo());
        livroAtualizado.setAutor(livro.getAutor());
        livroAtualizado.setAnoPublicacao(livro.getAnoPublicacao());
        livroAtualizado.setNota(livro.getNota());

        return livroRepository.save(livroAtualizado);
    }

    public Livro deletar(Long id) {
        Optional<Livro> livroAtualizado = livroRepository.findById(id);

        if(livroAtualizado.isPresent()) {
            livroRepository.delete(livroAtualizado.get());
            return livroAtualizado.get();
        }

        return null;

    }

}
