package carroproject.example.service;

import carroproject.example.dto.MarcaRequest;
import carroproject.example.exception.RecursoNaoEncontradoException;
import carroproject.example.exception.RegraDeNegocioException;
import carroproject.example.model.Marca;
import carroproject.example.repository.MarcaRepository;
import carroproject.example.repository.ModeloRepository;
import java.util.List;
import org.springframework.stereotype.Service;

// @Service: camada das regras de negócio (fica entre o controller e o repository)
@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;

    // Injeção de dependência: o Spring entrega os repositories prontos pelo construtor
    public MarcaService(MarcaRepository marcaRepository, ModeloRepository modeloRepository) {
        this.marcaRepository = marcaRepository;
        this.modeloRepository = modeloRepository;
    }

    public Marca criar(MarcaRequest dados) {
        // Regra: não permitir marcas repetidas
        if (marcaRepository.existsByNomeIgnoreCase(dados.nome())) {
            throw new RegraDeNegocioException("Já existe uma marca com o nome: " + dados.nome());
        }
        return marcaRepository.save(new Marca(dados.nome().trim()));
    }

    public List<Marca> listar() {
        return marcaRepository.findAll();
    }

    public Marca buscarPorId(Long id) {
        // orElseThrow: se o Optional estiver vazio, lança o erro 404
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Marca não encontrada: id " + id));
    }

    public Marca atualizar(Long id, MarcaRequest dados) {
        Marca marca = buscarPorId(id);
        marca.setNome(dados.nome().trim());
        return marcaRepository.save(marca);
    }

    public void remover(Long id) {
        Marca marca = buscarPorId(id);
        // Regra: não apagar marca que ainda tem modelos (quebraria a chave estrangeira)
        if (modeloRepository.existsByMarcaId(id)) {
            throw new RegraDeNegocioException("Não é possível excluir: a marca possui modelos cadastrados");
        }
        marcaRepository.delete(marca);
    }
}
