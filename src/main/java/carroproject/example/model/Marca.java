package carroproject.example.model;

import jakarta.persistence.*;

// @Entity: esta classe vira a tabela "marca" no banco
@Entity
// extends = HERANÇA: Marca ganha o "id" da EntidadeBase
public class Marca extends EntidadeBase {

    // Não aceita nulo e não permite duas marcas com o mesmo nome
    @Column(nullable = false, unique = true)
    private String nome;

    public Marca() {
    }

    public Marca(String nome) {
        this.nome = nome;
    }

    // Getter e setter = ENCAPSULAMENTO
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
