package me.jakub.githubreposapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class GithubReposApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubReposApiApplication.class, args);
    }

}
