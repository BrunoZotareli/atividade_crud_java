package matricula.com.demo.controller;

import matricula.com.demo.model.Livro;
import matricula.com.demo.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> criarLivro(@RequestBody Livro livro) {
        Livro novo = service.salvar(livro);
        return ResponseEntity.ok("Livro criado com ID " + novo.getId());
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        var livroOpt = service.buscarPorId(id);
        if (livroOpt.isPresent()) {
            return ResponseEntity.ok(livroOpt.get());
        } else {
            return ResponseEntity.status(404).body("Livro não encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        Livro atualizado = service.atualizar(id, livro);
        return ResponseEntity.ok("Livro atualizado com sucesso (ID " + atualizado.getId() + ")");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLivro(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok("Livro removido com sucesso");
    }

    @PostMapping("/importar")
    public ResponseEntity<?> importarLivros(@RequestBody List<Livro> livros) {
        int quantidade = service.importar(livros);
        return ResponseEntity.ok(quantidade + " livros importados com sucesso.");
    }
}
