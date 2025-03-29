package com.alvegag.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alvegag.portfolio.service.GithubService;
import com.alvegag.portfolio.model.GithubRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class GithubController {
  private final GithubService githubService;

  public GithubController(GithubService githubService) {
    this.githubService = githubService;
  }

  @GetMapping("/")
  public String showRepositories(Model model) {
    CompletableFuture<List<GithubRepository>> futureRepos = githubService.getUserRepositories();

    List<GithubRepository> repositories = futureRepos.join();

    model.addAttribute("repos", repositories);
    return "index";

  }

}
