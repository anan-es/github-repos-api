package me.jakub.githubreposapi;

import me.jakub.githubreposapi.model.GithubRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/github")
public class GithubReposController {

    @Autowired
    GithubService githubService;

    @GetMapping("/repositories/{username}")
    Flux<GithubRepositoryDTO> getRepositories(@PathVariable String username) {
        return githubService.getUserRepositories(username);
    }
}
