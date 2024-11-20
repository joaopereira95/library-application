package br.com.elotech.oxy.library.infrastructure.exceptionhandler.model;

import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.exception.RegraNegocioException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.List;

public record Problem(String title, int status, String detail, String type, List<Error> errors) {

    private static final String TYPE_DEFAULT_HOST = "http://www.biblioteca.com.br/";
    private static final String TYPE_INTERNAL_SERVER_ERROR = "InternalServerError";
    private static final String TYPE_NOT_FOUND_EXCEPTION = "ResourceNotFound";
    private static final String TYPE_BAD_REQUEST_EXCEPTION = "BadRequest";
    public static final String TITLE_EXCEPTION = "Erro interno do servidor";
    public static final String TITLE_INTEGRIDADE_DADOS = "Erro de integridade de dados";
    public static final String DETAIL_EXCEPTION = "Ocorreu um erro interno no servidor.";
    public static final String TITLE_RECURSO_NAO_ENCONTRADO = "Recurso não encontrado";
    public static final String TITLE_BAD_REQUEST = "Requisição inválida";
    public static final String DETAIL_METHOD_ARGUMENT_NOT_VALID = "Ocorreu um erro ao validar atributos de entrada.";
    public static final String MESSAGE_INVALID_FORMAT = "Valor %s inválido para o atributo.";
    public static final String DETAIL_ERRO_INTEGRIDADE = "Houve um erro de integridade dos dados.";

    public Problem(String title, int status, String detail, String type) { // boring!
        this(title, status, detail, type, null);
    }

    public static Problem newInternalServerErrorProblem() {
        return new Problem(
                TITLE_EXCEPTION,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                DETAIL_EXCEPTION,
                TYPE_DEFAULT_HOST.concat(TYPE_INTERNAL_SERVER_ERROR));
    }

    public static Problem newResourceNotFoundProblem(RecursoNaoEncontradoException ex) {
        return new Problem(
                TITLE_RECURSO_NAO_ENCONTRADO,
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                TYPE_DEFAULT_HOST.concat(TYPE_NOT_FOUND_EXCEPTION));
    }

    public static Problem newResourceNotFoundProblem(NoResourceFoundException ex) {
        return new Problem(
                TITLE_RECURSO_NAO_ENCONTRADO,
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                TYPE_DEFAULT_HOST.concat(TYPE_NOT_FOUND_EXCEPTION));
    }

    public static Problem newRegraNegocioProblem(RegraNegocioException ex) {
        return new Problem(
                TITLE_BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                TYPE_DEFAULT_HOST.concat(TYPE_BAD_REQUEST_EXCEPTION));
    }

    public static Problem newIntegridadeDadosProblem() {
        return new Problem(
                TITLE_INTEGRIDADE_DADOS,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                DETAIL_ERRO_INTEGRIDADE,
                TYPE_DEFAULT_HOST.concat(TYPE_INTERNAL_SERVER_ERROR));
    }

    public static Problem newArgumentNotValidProblem(MethodArgumentNotValidException ex) {

        List<Error> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new Error(error.getField(), error.getDefaultMessage()))
                .toList();

        return new Problem(
                TITLE_BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                DETAIL_METHOD_ARGUMENT_NOT_VALID,
                TYPE_DEFAULT_HOST.concat(TYPE_BAD_REQUEST_EXCEPTION),
                errors);
    }

    public static Problem newArgumentNotValidProblem(ConstraintViolationException ex) {

        List<Error> errors = ex.getConstraintViolations().stream()
                .map(error ->
                        new Error(error.getPropertyPath().toString(), error.getMessage()))
                .toList();

        return new Problem(
                TITLE_BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                DETAIL_METHOD_ARGUMENT_NOT_VALID,
                TYPE_DEFAULT_HOST.concat(TYPE_BAD_REQUEST_EXCEPTION),
                errors);
    }

    public static Problem newArgumentNotValidProblem(HttpMessageNotReadableException ex) {

        List<Error> errors = new ArrayList<>();

        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
            var value = invalidFormatException.getValue();
            var typeName = invalidFormatException.getPath().getFirst().getFieldName();

            errors.add(new Error(typeName, MESSAGE_INVALID_FORMAT.formatted(value)));
        }

        return new Problem(
                TITLE_BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                DETAIL_METHOD_ARGUMENT_NOT_VALID,
                TYPE_DEFAULT_HOST.concat(TYPE_BAD_REQUEST_EXCEPTION),
                errors);
    }


}
