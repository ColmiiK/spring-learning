package com.alvegag.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepository {
  private String name;

  @JsonProperty("html_url")
  private String htmlUrl;

  private String description;
  private String language;

  @JsonProperty("pushed_at")
  private Date pushedAt;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("private")
  private boolean privateRepo;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Date getPushedAt() {
    return pushedAt;
  }

  public void setPushedAt(Date pushedAt) {
    this.pushedAt = pushedAt;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isPrivateRepo() {
    return privateRepo;
  }

  public void setPrivateRepo(boolean privateRepo) {
    this.privateRepo = privateRepo;
  }
}
