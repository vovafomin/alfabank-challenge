package com.vovafomin.alfabankchallenge.client.resource;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GiphySearchResource {

  private List<GiphyDataResource> data;
}
