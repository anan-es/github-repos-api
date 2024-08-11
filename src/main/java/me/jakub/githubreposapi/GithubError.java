package me.jakub.githubreposapi;

import org.springframework.http.HttpStatus;

public record GithubError(HttpStatus status, String message) {}
