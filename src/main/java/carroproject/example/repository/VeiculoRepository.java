package carroproject.example.repository;

import carroproject.example.model.StatusVeiculo;
import carroproject.example.model.Veiculo;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    // Verifica se o modelo está sendo usado por algum veículo (antes de excluir o modelo)
    boolean existsByModeloId(Long modeloId);

    // Filtro com todos os parâmetros OPCIONAIS: "(:x IS NULL OR condição)"
    // Se o parâmetro vier nulo, aquela condição é ignorada
    @Query("""
            SELECT v FROM Veiculo v
            WHERE (:marca IS NULL OR LOWER(v.modelo.marca.nome) = LOWER(:marca))
              AND (:modelo IS NULL OR LOWER(v.modelo.nome) = LOWER(:modelo))
              AND (:ano IS NULL OR v.ano = :ano)
              AND (:precoMin IS NULL OR v.preco >= :precoMin)
              AND (:precoMax IS NULL OR v.preco <= :precoMax)
              AND (:status IS NULL OR v.status = :status)
            ORDER BY v.id
            """)
    List<Veiculo> filtrar(@Param("marca") String marca,
                          @Param("modelo") String modelo,
                          @Param("ano") Integer ano,
                          @Param("precoMin") BigDecimal precoMin,
                          @Param("precoMax") BigDecimal precoMax,
                          @Param("status") StatusVeiculo status);
}
