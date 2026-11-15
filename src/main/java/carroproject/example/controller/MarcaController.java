package carroproject.example.controller;

import carroproject.example.dto.MarcaRequest;
import carroproject.example.model.Marca;
import carroproject.example.service.MarcaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// @RestController: recebe requisições HTTP e responde em JSON
@RestController
// Todas as rotas desta classe começam com /marcas
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService service;

    public MarcaController(MarcaService service) {
        this.service = service;
    }

    // POST /marcas  -> cria (201 Created)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // @Valid roda as validações do DTO; @RequestBody lê o JSON do corpo
    public Marca criar(@Valid @RequestBody MarcaRequest dados) {
        return service.criar(dados);
    }

    // GET /marcas -> lista todas
    @GetMapping
    public List<Marca> listar() {
        return service.listar();
    }

    // GET /marcas/1 -> busca uma (o {id} da URL vira o parâmetro)
    @GetMapping("/{id}")
    public Marca buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // PUT /marcas/1 -> atualiza
    @PutMapping("/{id}")
    public Marca atualizar(@PathVariable Long id, @Valid @RequestBody MarcaRequest dados) {
        return service.atualizar(id, dados);
    }

    // DELETE /marcas/1 -> remove (204 No Content)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}
