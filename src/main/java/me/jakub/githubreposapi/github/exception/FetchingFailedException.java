package me.jakub.githubreposapi.github.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class FetchingFailedException extends RuntimeException {
    private final HttpStatusCode statusCode;
    private final String responseString;

    public FetchingFailedException(HttpStatusCode statusCode, String responseString) {
        this.statusCode = statusCode;
        this.responseString = responseString;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public String getResponseString() {
        return responseString;
    }
}
