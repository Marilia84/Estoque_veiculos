package carroproject.example.repository;

import carroproject.example.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entidade, TipoDoId> já traz save, findAll, findById, deleteById... sem escrever SQL
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    // O Spring entende o nome do método e gera o SQL: SELECT COUNT(*) > 0 WHERE LOWER(nome) = ?
    boolean existsByNomeIgnoreCase(String nome);
}
