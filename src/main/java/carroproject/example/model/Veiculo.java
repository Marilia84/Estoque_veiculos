package carroproject.example.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Veiculo extends EntidadeBase {

    // Muitos veículos são de UM modelo (e o modelo já carrega a marca)
    @ManyToOne(optional = false)
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    @Column(nullable = false)
    private Integer ano;

    private String cor;

    // BigDecimal para dinheiro (double tem erro de arredondamento)
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;

    private Integer quilometragem;

    // STRING: salva o texto ("DISPONIVEL") em vez do número da posição, mais seguro
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusVeiculo status = StatusVeiculo.DISPONIVEL;

    public Veiculo() {
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }
}
