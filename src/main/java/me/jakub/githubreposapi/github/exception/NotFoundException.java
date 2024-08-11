package me.jakub.githubreposapi.github.exception;

import org.springframework.web.reactive.function.client.ClientResponse;

public class NotFoundException extends Exception {
    ClientResponse clientResponse;
    public NotFoundException(ClientResponse clientResponse) {
        this.clientResponse = clientResponse;
    }
}
