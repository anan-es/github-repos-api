package me.jakub.githubreposapi;

import me.jakub.githubreposapi.github.GithubService;
import me.jakub.githubreposapi.github.model.Branch;
import me.jakub.githubreposapi.github.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/github")
public class GithubReposController {
    @Autowired
    GithubService githubService;

    @GetMapping("/repositories/{username}")
    Flux<Repository> getRepositories(@PathVariable String username) {
        return githubService.getUserRepositories(username);
    }

    @GetMapping("/branches/{username}/{repository}")
    Flux<Branch> getRepositories(@PathVariable String username, @PathVariable String repository) {
        return githubService.getUserRepositoryBranches(username, repository);
    }
}
