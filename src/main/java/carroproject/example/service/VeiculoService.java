package carroproject.example.service;

import carroproject.example.dto.AtualizaVeiculoRequest;
import carroproject.example.dto.VeiculoRequest;
import carroproject.example.exception.RecursoNaoEncontradoException;
import carroproject.example.model.StatusVeiculo;
import carroproject.example.model.Veiculo;
import carroproject.example.repository.VeiculoRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ModeloService modeloService;

    public VeiculoService(VeiculoRepository veiculoRepository, ModeloService modeloService) {
        this.veiculoRepository = veiculoRepository;
        this.modeloService = modeloService;
    }

    // CREATE
    public Veiculo criar(VeiculoRequest dados) {
        Veiculo veiculo = new Veiculo();
        // Converte o DTO (o que o cliente mandou) na entidade (o que vai pro banco)
        veiculo.setModelo(modeloService.buscarPorId(dados.modeloId()));
        veiculo.setAno(dados.ano());
        veiculo.setCor(dados.cor());
        veiculo.setPreco(dados.preco());
        veiculo.setQuilometragem(dados.quilometragem());
        // Se não informou status, começa como DISPONIVEL
        veiculo.setStatus(dados.status() != null ? dados.status() : StatusVeiculo.DISPONIVEL);
        return veiculoRepository.save(veiculo);
    }

    // READ (lista com filtros opcionais)
    public List<Veiculo> filtrar(String marca, String modelo, Integer ano,
                                 BigDecimal precoMin, BigDecimal precoMax, StatusVeiculo status) {
        return veiculoRepository.filtrar(marca, modelo, ano, precoMin, precoMax, status);
    }

   
    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado: id " + id));
    }

    // UPDATE (só preço, quilometragem e status, como pede o enunciado)
    public Veiculo atualizar(Long id, AtualizaVeiculoRequest dados) {
        Veiculo veiculo = buscarPorId(id);
        // Só altera o que foi enviado (não nulo)
        if (dados.preco() != null) {
            veiculo.setPreco(dados.preco());
        }
        if (dados.quilometragem() != null) {
            veiculo.setQuilometragem(dados.quilometragem());
        }
        if (dados.status() != null) {
            veiculo.setStatus(dados.status());
        }
        return veiculoRepository.save(veiculo);
    }

    // DELETE
    public void remover(Long id) {
        veiculoRepository.delete(buscarPorId(id));
    }
}
