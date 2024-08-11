package me.jakub.githubreposapi.github.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "api.github")
public record GithubConfiguration(
        String url,
        String token,
        Integer maxPerPage) {
    @ConstructorBinding
    public GithubConfiguration {
        if (maxPerPage == null) {
            maxPerPage = 100;
        }
    }
}
