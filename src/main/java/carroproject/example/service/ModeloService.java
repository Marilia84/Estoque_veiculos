package carroproject.example.service;

import carroproject.example.dto.ModeloRequest;
import carroproject.example.exception.RecursoNaoEncontradoException;
import carroproject.example.exception.RegraDeNegocioException;
import carroproject.example.model.Marca;
import carroproject.example.model.Modelo;
import carroproject.example.repository.ModeloRepository;
import carroproject.example.repository.VeiculoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ModeloService {

    private final ModeloRepository modeloRepository;
    private final VeiculoRepository veiculoRepository;
    private final MarcaService marcaService;

    public ModeloService(ModeloRepository modeloRepository, VeiculoRepository veiculoRepository,
                         MarcaService marcaService) {
        this.modeloRepository = modeloRepository;
        this.veiculoRepository = veiculoRepository;
        this.marcaService = marcaService;
    }

    public Modelo criar(ModeloRequest dados) {
        // Busca a marca (se o id não existir, já lança 404)
        Marca marca = marcaService.buscarPorId(dados.marcaId());
        return modeloRepository.save(new Modelo(dados.nome().trim(), marca));
    }

    // Se marcaId vier preenchido, lista só os modelos daquela marca
    public List<Modelo> listar(Long marcaId) {
        if (marcaId != null) {
            return modeloRepository.findByMarcaId(marcaId);
        }
        return modeloRepository.findAll();
    }

    public Modelo buscarPorId(Long id) {
        return modeloRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Modelo não encontrado: id " + id));
    }

    public Modelo atualizar(Long id, ModeloRequest dados) {
        Modelo modelo = buscarPorId(id);
        modelo.setNome(dados.nome().trim());
        modelo.setMarca(marcaService.buscarPorId(dados.marcaId()));
        return modeloRepository.save(modelo);
    }

    public void remover(Long id) {
        Modelo modelo = buscarPorId(id);
        // Regra: não apagar modelo que ainda tem veículos no estoque
        if (veiculoRepository.existsByModeloId(id)) {
            throw new RegraDeNegocioException("Não é possível excluir: o modelo possui veículos cadastrados");
        }
        modeloRepository.delete(modelo);
    }
}
