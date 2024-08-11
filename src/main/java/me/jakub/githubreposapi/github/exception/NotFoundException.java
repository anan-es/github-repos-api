package me.jakub.githubreposapi.github.exception;

import org.springframework.web.reactive.function.client.ClientResponse;

public class NotFoundException extends RuntimeException {
    private final String statusText;

    public NotFoundException(String statusText) {
        this.statusText = statusText;
    }

    public String getStatusText() {
        return statusText;
    }
}
