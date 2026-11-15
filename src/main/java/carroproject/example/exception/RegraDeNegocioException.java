package carroproject.example.exception;

// Lançada quando o pedido é válido no formato, mas quebra uma regra (ex.: marca duplicada)
public class RegraDeNegocioException extends RuntimeException {

    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }
}
