package com.vovafomin.alfabankchallenge.client.resource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GiphyImagesResource {

  private GiphyImagesOriginalResource original;
}
