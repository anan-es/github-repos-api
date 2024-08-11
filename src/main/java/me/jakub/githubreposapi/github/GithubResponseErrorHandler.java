package me.jakub.githubreposapi.github;

import me.jakub.githubreposapi.github.exception.FetchingFailedException;
import me.jakub.githubreposapi.github.exception.NotFoundException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;

import java.io.IOException;

@Component
public class GithubResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is5xxServerError() || response.getStatusCode().is4xxClientError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()) {
            throw new NotFoundException(response.getStatusText());
        }
        throw new FetchingFailedException(response.getStatusCode(), response.getStatusText());
    }
}
