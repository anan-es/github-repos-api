package me.jakub.githubreposapi.github;

import me.jakub.githubreposapi.github.model.Branch;
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
    ResponseEntity<List<Repository>> getUserRepos(@PathVariable String username, @RequestParam Integer page, @RequestParam("per_page") Integer perPage);

    @GetExchange
    ResponseEntity<List<Repository>> getUserRepos(URI uri);

    @GetExchange("/repos/{username}/{repository}/branches")
    ResponseEntity<List<Branch>> getUserRepoBranches(@PathVariable String username, @PathVariable String repository);

    @GetExchange
    ResponseEntity<List<Branch>> getUserRepoBranches(URI uri);

}
