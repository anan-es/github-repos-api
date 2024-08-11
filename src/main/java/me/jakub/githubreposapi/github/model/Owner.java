package me.jakub.githubreposapi.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {
    @JsonProperty
    private String login;
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String url;
}
