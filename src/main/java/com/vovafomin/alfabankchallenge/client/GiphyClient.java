package com.vovafomin.alfabankchallenge.client;

import java.util.Map;

import com.vovafomin.alfabankchallenge.client.resource.GiphySearchResource;

import feign.QueryMap;
import feign.RequestLine;

public interface GiphyClient {

  @RequestLine("GET /gifs/search")
  GiphySearchResource search(@QueryMap Map<String, String> queryMap);
}
