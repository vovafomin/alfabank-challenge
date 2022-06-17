package com.vovafomin.alfabankchallenge.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vovafomin.alfabankchallenge.service.CurrencyNotFoundException;
import com.vovafomin.alfabankchallenge.service.EvaluationService;
import com.vovafomin.alfabankchallenge.service.GiphyService;

@RestController
@RequestMapping("/evaluate")
public class EvaluateController {

  private EvaluationService evaluationService;
  private GiphyService giphyService;

  public EvaluateController(
      EvaluationService evaluationService,
      GiphyService giphyService) {
    this.evaluationService = evaluationService;
    this.giphyService = giphyService;
  }

  @GetMapping("/{code}")
  public ResponseEntity<?> rate(@PathVariable("code") String code,
      @RequestParam(name = "minusDays", defaultValue = "1") int minusDays) {
    try {
      String tag = evaluationService.getTag(code, minusDays);
      String url = giphyService.getUrl(tag);
      return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    } catch (CurrencyNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
