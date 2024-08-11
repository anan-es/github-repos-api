package me.jakub.githubreposapi.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {
    @JsonProperty
    String name;
    @JsonProperty
    Commit commit;

    public String getName() {
        return name;
    }

    public Commit getCommit() {
        return commit;
    }
}
