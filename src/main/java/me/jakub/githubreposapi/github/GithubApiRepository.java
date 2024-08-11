package me.jakub.githubreposapi.github;

import me.jakub.githubreposapi.github.model.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.net.URI;
import java.util.List;

@HttpExchange(accept = "application/vnd.github.v3+json")
interface GithubApiRepository {
    @GetExchange("/users/{username}/repos")
    ResponseEntity<List<Repository>> getUserReposPage(@PathVariable String username, @RequestParam Integer page, @RequestParam("per_page") Integer perPage);

    @GetExchange
    ResponseEntity<List<Repository>> getUserRepos(URI uri);
}
