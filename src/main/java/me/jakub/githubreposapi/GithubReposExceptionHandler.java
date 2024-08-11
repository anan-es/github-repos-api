package me.jakub.githubreposapi;

import me.jakub.githubreposapi.github.exception.FetchingFailedException;
import me.jakub.githubreposapi.github.exception.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GithubReposExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        return constructResponse(new GithubError(HttpStatus.NOT_FOUND, e.getStatusText()));
    }

    @ExceptionHandler(FetchingFailedException.class)
    protected ResponseEntity<Object> handleBadRequestException(FetchingFailedException e) {
        return constructResponse(new GithubError(HttpStatus.resolve(e.getStatusCode().value()), e.getResponseString()));
    }

    private ResponseEntity<Object> constructResponse(GithubError githubError) {
        return new ResponseEntity<>(githubError, githubError.status());
    }
}
