package me.jakub.githubreposapi;

import me.jakub.githubreposapi.github.GithubApiService;
import me.jakub.githubreposapi.model.GithubRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GithubService {
    @Autowired
    GithubApiService githubApiService;

    public Flux<GithubRepositoryDTO> getUserRepositories(String username) {
        return githubApiService.getUserRepositories(username)
                .filter(repository -> !repository.getFork())
                .flatMap(repository -> githubApiService.getUserRepositoryBranches(
                        username, repository.getName()).collectList()
                        .map(branches -> GithubMapper.toDto(repository, branches)));
    }
}
