package carroproject.example.repository;

import carroproject.example.model.Modelo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {

    // Lista os modelos de uma marca: navega Modelo -> marca -> id
    List<Modelo> findByMarcaId(Long marcaId);

    // Verifica se a marca ainda tem modelos (usado antes de excluir a marca)
    boolean existsByMarcaId(Long marcaId);
}
