package com.alvegag.portfolio.service;

import com.alvegag.portfolio.model.GithubRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public class GithubService {

  private final RestTemplate restTemplate;

  Dotenv dotenv = Dotenv.load();
  private String githubToken = dotenv.get("GITHUB_TOKEN");
  // private String githubToken = System.getenv("GITHUB_TOKEN");

  public GithubService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Async
  public CompletableFuture<List<GithubRepository>> getUserRepositories() {
    String url = "https://api.github.com/user/repos";

    HttpHeaders headers = new HttpHeaders();

    headers.set("Authorization", "Bearer " + githubToken);
    headers.set("Accept", "application/vnd.github.v3+json");
    HttpEntity<String> entity = new HttpEntity<>(headers);

    try {
      ResponseEntity<GithubRepository[]> response = restTemplate.exchange(url, HttpMethod.GET, entity,
          GithubRepository[].class);
      List<GithubRepository> repos = Arrays.asList(response.getBody());

      return CompletableFuture
          .completedFuture(repos.stream().filter(repo -> !repo.isPrivateRepo()).collect(Collectors.toList()));

    } catch (Exception e) {
      e.printStackTrace();
      return CompletableFuture.completedFuture(List.of());
    }
  }
}
