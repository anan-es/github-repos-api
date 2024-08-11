package me.jakub.githubreposapi.model;

import java.util.List;

public record GithubRepositoryDTO(String name, String ownerLogin, List<GithubBranchDTO> branches) {}
