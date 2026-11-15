package carroproject.example.controller;

import carroproject.example.dto.AtualizaVeiculoRequest;
import carroproject.example.dto.VeiculoRequest;
import carroproject.example.model.StatusVeiculo;
import carroproject.example.model.Veiculo;
import carroproject.example.service.VeiculoService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    // POST /veiculos
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo criar(@Valid @RequestBody VeiculoRequest dados) {
        return service.criar(dados);
    }

    // GET /veiculos?marca=Fiat&modelo=Uno&ano=2020&precoMin=20000&precoMax=50000&status=DISPONIVEL
    // Todos os parâmetros são opcionais; sem nenhum, lista tudo
    @GetMapping
    public List<Veiculo> listar(@RequestParam(required = false) String marca,
                                @RequestParam(required = false) String modelo,
                                @RequestParam(required = false) Integer ano,
                                @RequestParam(required = false) BigDecimal precoMin,
                                @RequestParam(required = false) BigDecimal precoMax,
                                @RequestParam(required = false) StatusVeiculo status) {
        return service.filtrar(marca, modelo, ano, precoMin, precoMax, status);
    }

    // GET /veiculos/1
    @GetMapping("/{id}")
    public Veiculo buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // PUT /veiculos/1  (preço, quilometragem, status)
    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable Long id, @Valid @RequestBody AtualizaVeiculoRequest dados) {
        return service.atualizar(id, dados);
    }

    // DELETE /veiculos/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}
