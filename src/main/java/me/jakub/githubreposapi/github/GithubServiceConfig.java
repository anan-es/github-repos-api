package me.jakub.githubreposapi.github;

import me.jakub.githubreposapi.github.config.GithubConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class GithubServiceConfig {
    @Autowired
    GithubConfiguration githubConfiguration;

    @Bean
    RestClient githubRestClient() {
        return RestClient.builder()
                .baseUrl(githubConfiguration.apiUrl())
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .build();
    }

    @Bean
    GithubApiRepository githubApiService() {
        RestClientAdapter adapter = RestClientAdapter.create(githubRestClient());
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(GithubApiRepository.class);
    }
}
