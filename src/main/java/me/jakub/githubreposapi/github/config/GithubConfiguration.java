package me.jakub.githubreposapi.github.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("api.github")
public record GithubConfiguration(
        String apiUrl,
        String apiKey,
        Integer maxPerPage) {
    public GithubConfiguration {
        if (maxPerPage == null) {
            maxPerPage = 100;
        }
    }
}
