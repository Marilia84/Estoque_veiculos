package carroproject.example.model;

import jakarta.persistence.*;

@Entity
public class Modelo extends EntidadeBase {

    @Column(nullable = false)
    private String nome;

    // Muitos modelos pertencem a UMA marca (ex.: Gol, Polo → Volkswagen)
    @ManyToOne(optional = false)
    // Cria a coluna "marca_id" (chave estrangeira) na tabela modelo
    @JoinColumn(name = "marca_id")
    private Marca marca;

    public Modelo() {
    }

    public Modelo(String nome, Marca marca) {
        this.nome = nome;
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
