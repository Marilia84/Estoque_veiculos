package carroproject.example.dto;

import carroproject.example.model.StatusVeiculo;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

// Só os campos que o enunciado manda atualizar: preço, quilometragem e status
// Todos opcionais: quem não for enviado (null) permanece como está
public record AtualizaVeiculoRequest(
        @Positive(message = "O preço deve ser maior que zero") BigDecimal preco,
        @PositiveOrZero(message = "A quilometragem não pode ser negativa") Integer quilometragem,
        StatusVeiculo status) {
}
