package com.vovafomin.alfabankchallenge.client;

import java.util.Map;

import com.vovafomin.alfabankchallenge.client.resource.OpenExchangeRatesResource;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

public interface OpenExchangeRatesClient {

  @RequestLine("GET /latest.json")
  OpenExchangeRatesResource latest(@QueryMap Map<String, String> queryMap);

  @RequestLine("GET /historical/{date}.json")
  OpenExchangeRatesResource historical(@Param("date") String date, @QueryMap Map<String, String> queryMap);
}
