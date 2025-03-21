package br.com.exemplo.apilivro.repository;

import br.com.exemplo.apilivro.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {}
