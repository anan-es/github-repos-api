package me.jakub.githubreposapi.github;

import me.jakub.githubreposapi.github.config.GithubConfiguration;
import me.jakub.githubreposapi.github.exception.FetchingFailedException;
import me.jakub.githubreposapi.github.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Links;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;

@Service
public class GithubService {
    @Autowired
    private GithubApiRepository githubApiRepository;

    @Autowired
    private GithubConfiguration githubConfiguration;

    public Flux<Repository> getUserRepositories(String username) {
        return getUserRepositoriesPage(username).expand(listResponseEntity -> {
            String paginationLinks = listResponseEntity.getHeaders().getFirst("Link");
            if (paginationLinks == null) {
                return Mono.empty();
            }
            Links relativeLinks = Links.parse(paginationLinks);
            // Expand the flux with next pages
            return relativeLinks.getLink("next")
                    .map(nextLink -> githubApiRepository.getUserRepos(nextLink.toUri()))
                    .map(Mono::just).orElseGet(Mono::empty);

        }).mapNotNull(ResponseEntity::getBody).flatMap(Flux::fromIterable);
    }

    private Mono<ResponseEntity<List<Repository>>> getUserRepositoriesPage(String username) {
        ResponseEntity<List<Repository>> userReposPage = githubApiRepository.getUserReposPage(username, 0, githubConfiguration.maxPerPage());
        return Mono.just(userReposPage);
    }
}
