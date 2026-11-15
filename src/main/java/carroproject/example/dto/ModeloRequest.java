package carroproject.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModeloRequest(
        @NotBlank(message = "O nome do modelo é obrigatório") String nome,
        // Em vez de mandar a marca inteira, o cliente manda só o id dela
        @NotNull(message = "O id da marca é obrigatório") Long marcaId) {
}
