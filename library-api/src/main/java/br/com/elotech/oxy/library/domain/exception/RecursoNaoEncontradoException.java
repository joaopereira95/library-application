package br.com.elotech.oxy.library.domain.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(Integer id, Class<?> classe) {
        super("O recurso '%s' com identificador '%d' não foi encontrado.".formatted(classe.getSimpleName(), id));
    }

    public RecursoNaoEncontradoException(String id, Class<?> classe) {
        super("O recurso '%s' com identificador '%s' não foi encontrado.".formatted(classe.getSimpleName(), id));
    }
}
