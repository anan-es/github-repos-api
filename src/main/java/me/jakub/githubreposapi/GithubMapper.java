package me.jakub.githubreposapi;

import me.jakub.githubreposapi.github.model.Branch;
import me.jakub.githubreposapi.github.model.Repository;
import me.jakub.githubreposapi.model.GithubBranchDTO;
import me.jakub.githubreposapi.model.GithubRepositoryDTO;

import java.util.List;

public class GithubMapper {
    public static GithubRepositoryDTO toDto(Repository repository, List<Branch> branches) {
        List<GithubBranchDTO> branchDTOList = branches.stream()
                .map(branch -> new GithubBranchDTO(branch.getName(), branch.getCommit().getSha())).toList();
        return new GithubRepositoryDTO(
                repository.getName(), repository.getOwner().getLogin(), branchDTOList);
    }
}
