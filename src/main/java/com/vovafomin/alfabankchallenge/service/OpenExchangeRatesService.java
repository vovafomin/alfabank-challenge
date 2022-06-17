package com.vovafomin.alfabankchallenge.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vovafomin.alfabankchallenge.client.OpenExchangeRatesClient;
import com.vovafomin.alfabankchallenge.client.resource.OpenExchangeRatesResource;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

@Service
public class OpenExchangeRatesService {

  private OpenExchangeRatesClient client;
  private String appId;

  @Autowired
  public OpenExchangeRatesService(
      @Value("${app.openexchangerates.base-url}") String baseUrl,
      @Value("${app.openexchangerates.app-id}") String appId) {
    this.appId = appId;
    client = Feign.builder()
        .client(new OkHttpClient())
        .encoder(new GsonEncoder())
        .decoder(new GsonDecoder())
        .logger(new Slf4jLogger(OpenExchangeRatesClient.class))
        .logLevel(Logger.Level.FULL)
        .target(OpenExchangeRatesClient.class, baseUrl);
  }

  public OpenExchangeRatesService(OpenExchangeRatesClient client) {
    this.client = client;
  }

  public OpenExchangeRatesResource latest() {
    Map<String, String> queryMap = new HashMap<>();
    queryMap.put("app_id", appId);
    return client.latest(queryMap);
  }

  public OpenExchangeRatesResource historical(LocalDate date) {
    Map<String, String> queryMap = new HashMap<>();
    queryMap.put("app_id", appId);
    return client.historical(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), queryMap);
  }
}
