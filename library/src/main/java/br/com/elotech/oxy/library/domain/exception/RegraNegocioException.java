package br.com.elotech.oxy.library.domain.exception;

public class RegraNegocioException extends  RuntimeException {

    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
