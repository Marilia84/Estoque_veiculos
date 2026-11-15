package carroproject.example.exception;

// Exceção própria: lançada quando um id não existe no banco
// RuntimeException = "não verificada", não precisa de try/catch em todo lugar
public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagem) {
        // Repassa a mensagem para a classe mãe (RuntimeException)
        super(mensagem);
    }
}
