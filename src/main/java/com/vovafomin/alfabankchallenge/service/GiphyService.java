package com.vovafomin.alfabankchallenge.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vovafomin.alfabankchallenge.client.GiphyClient;
import com.vovafomin.alfabankchallenge.client.resource.GiphySearchResource;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

@Service
public class GiphyService {

  private GiphyClient client;
  private String apiKey;

  @Autowired
  public GiphyService(
      @Value("${app.giphy.base-url}") String baseUrl,
      @Value("${app.giphy.api-key}") String apiKey) {
    this.apiKey = apiKey;
    client = Feign.builder()
        .client(new OkHttpClient())
        .encoder(new GsonEncoder())
        .decoder(new GsonDecoder())
        .logger(new Slf4jLogger(GiphyClient.class))
        .logLevel(Logger.Level.FULL)
        .target(GiphyClient.class, baseUrl);
  }

  public GiphyService(GiphyClient client) {
    this.client = client;
  }

  public String getUrl(String tag) {
    Map<String, String> queryMap = new HashMap<>();
    queryMap.put("q", tag);
    queryMap.put("limit", "10");
    queryMap.put("api_key", apiKey);
    GiphySearchResource resource = client.search(queryMap);
    int rand = 1 + (int) (Math.random() * 10);
    return resource.getData().get(rand - 1).getImages().getOriginal().getUrl();
  }
}