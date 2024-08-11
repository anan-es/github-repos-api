package me.jakub.githubreposapi.github.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String nodeId;
    @JsonProperty
    private String name;
    @JsonProperty
    private Owner owner;
    @JsonProperty
    private Boolean fork;
    @JsonProperty
    private String url;
}
