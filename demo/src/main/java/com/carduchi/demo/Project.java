package com.carduchi.demo;

import jakarta.persistence.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String githubLink;
    private String imageUrl;

    public Project() {}

    public Project(String title, String description, String githubLink, String imageUrl) {
        this.title = title;
        this.description = description;
        this.githubLink = githubLink;
        this.imageUrl = imageUrl;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getGithubLink() { return githubLink; }
    public String getImageUrl() { return imageUrl; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
