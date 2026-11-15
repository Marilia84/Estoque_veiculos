package carroproject.example.model;

import jakarta.persistence.*;

// @MappedSuperclass: NÃO vira tabela, mas as colunas dela são herdadas pelas classes filhas (HERANÇA)
@MappedSuperclass
public abstract class EntidadeBase {

    // @Id: chave primária
    @Id
    // IDENTITY: o MySQL gera o número sozinho (auto_increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ENCAPSULAMENTO: atributo private, acesso só pelo getter
    public Long getId() {
        return id;
    }
}
