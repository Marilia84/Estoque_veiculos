package carroproject.example.dto;

import jakarta.validation.constraints.NotBlank;

// record = classe enxuta só para carregar dados (DTO). Os campos viram atributos finais com getters automáticos
// DTO = o formato do JSON que o cliente envia, separado da entidade do banco
public record MarcaRequest(
        // @NotBlank: não aceita nulo, vazio ou só espaços
        @NotBlank(message = "O nome da marca é obrigatório") String nome) {
}
