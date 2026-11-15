package carroproject.example.controller;

import carroproject.example.dto.ModeloRequest;
import carroproject.example.model.Modelo;
import carroproject.example.service.ModeloService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modelos")
public class ModeloController {

    private final ModeloService service;

    public ModeloController(ModeloService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Modelo criar(@Valid @RequestBody ModeloRequest dados) {
        return service.criar(dados);
    }

    // GET /modelos  ou  GET /modelos?marcaId=1  (required = false: o filtro é opcional)
    @GetMapping
    public List<Modelo> listar(@RequestParam(required = false) Long marcaId) {
        return service.listar(marcaId);
    }

    @GetMapping("/{id}")
    public Modelo buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Modelo atualizar(@PathVariable Long id, @Valid @RequestBody ModeloRequest dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}
