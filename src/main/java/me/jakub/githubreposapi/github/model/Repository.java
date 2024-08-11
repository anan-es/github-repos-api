package me.jakub.githubreposapi.github.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {
    private Integer id;
    private String nodeId;
    private String name;
    private Owner owner;
    private Boolean fork;
    private String url;
}
