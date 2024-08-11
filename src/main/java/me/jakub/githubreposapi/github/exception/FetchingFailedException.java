package me.jakub.githubreposapi.github.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;

public class FetchingFailedException extends RuntimeException {
    private final HttpStatusCode statusCode;
    private final String responseString;

    public FetchingFailedException(HttpStatusCode statusCode, String responseString) {
        this.statusCode = statusCode;
        this.responseString = responseString;
    }
}
