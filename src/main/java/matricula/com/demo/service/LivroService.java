package matricula.com.demo.service;

import matricula.com.demo.model.Livro;
import matricula.com.demo.repository.LivroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Livro atualizar(Long id, Livro novoLivro) {
        return repository.findById(id)
                .map(livro -> {
                    livro.setNome(novoLivro.getNome());
                    livro.setIdade(novoLivro.getIdade());
                    livro.setCurso(novoLivro.getCurso());
                    return repository.save(livro);
                })
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public int importar(List<Livro> livros) {
        repository.saveAll(livros);
        return livros.size();
    }
}
