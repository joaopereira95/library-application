package br.com.elotech.oxy.library.infrastructure.exceptionhandler;

import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.exception.RegraNegocioException;
import br.com.elotech.oxy.library.infrastructure.exceptionhandler.model.Problem;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class LibraryExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Problem> exception(Exception ex) {
        return new ResponseEntity<>(Problem.newInternalServerErrorProblem(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Problem> recursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
        return new ResponseEntity<>(Problem.newResourceNotFoundProblem(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Problem> regraNegocioException(RegraNegocioException ex) {
        return new ResponseEntity<>(Problem.newRegraNegocioProblem(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(Problem.newArgumentNotValidProblem(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Problem> httpMessageNotReadableExceptionException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(Problem.newArgumentNotValidProblem(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Problem> noResourceFoundException(NoResourceFoundException ex) {
        return new ResponseEntity<>(Problem.newResourceNotFoundProblem(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Problem> dataIntegrityViolationException() {
        return new ResponseEntity<>(Problem.newIntegridadeDadosProblem(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Problem> constraintViolationException(ConstraintViolationException ex) {
        return new ResponseEntity<>(Problem.newArgumentNotValidProblem(ex), HttpStatus.BAD_REQUEST);
    }

}
