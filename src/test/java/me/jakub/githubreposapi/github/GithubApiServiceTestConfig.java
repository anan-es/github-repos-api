package me.jakub.githubreposapi.github;

import me.jakub.githubreposapi.github.config.GithubConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@Profile("test")
public class GithubApiServiceTestConfig {
    @Autowired
    GithubConfiguration githubConfiguration;

    @Bean
    RestClient githubRestClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8090")
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .defaultStatusHandler(new GithubApiResponseErrorHandler())
                .build();
    }

    @Bean
    GithubApiRepository githubApiRepository() {
        RestClientAdapter adapter = RestClientAdapter.create(githubRestClient());
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(GithubApiRepository.class);
    }
}
