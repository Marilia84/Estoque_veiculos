package carroproject.example.dto;

import carroproject.example.model.StatusVeiculo;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record VeiculoRequest(
        @NotNull(message = "O id do modelo é obrigatório") Long modeloId,
        // Faixa de ano aceita (evita valores absurdos como 3000)
        @NotNull(message = "O ano é obrigatório") @Min(value = 1900, message = "Ano inválido") @Max(value = 2100, message = "Ano inválido") Integer ano,
        String cor,
        // @Positive: precisa ser maior que zero
        @NotNull(message = "O preço é obrigatório") @Positive(message = "O preço deve ser maior que zero") BigDecimal preco,
        // @PositiveOrZero: zero é permitido (carro 0 km)
        @PositiveOrZero(message = "A quilometragem não pode ser negativa") Integer quilometragem,
        // Se vier nulo, o service assume DISPONIVEL
        StatusVeiculo status) {
}
