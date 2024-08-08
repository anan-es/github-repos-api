package me.jakub.githubreposapi.github;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

public class GithubApiClient {
    /*
        URL of the GitHub API
     */
    @Value("api.github.url")
    private String apiUrl;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .build();
    }

}
