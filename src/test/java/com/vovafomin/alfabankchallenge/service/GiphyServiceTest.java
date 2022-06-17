package com.vovafomin.alfabankchallenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.vovafomin.alfabankchallenge.client.GiphyClient;
import com.vovafomin.alfabankchallenge.client.resource.GiphyDataResource;
import com.vovafomin.alfabankchallenge.client.resource.GiphyImagesOriginalResource;
import com.vovafomin.alfabankchallenge.client.resource.GiphyImagesResource;
import com.vovafomin.alfabankchallenge.client.resource.GiphySearchResource;

@SpringBootTest
public class GiphyServiceTest {

  @Mock
  private GiphyClient giphyClient;

  @Test
  public void getUrlReturnsCorrectValue() {
    String url = "https://media0.giphy.com/media/3oFzlWFLgj0t6LhEyY/giphy.gif?cid=2eacdcd9ov2td8zi96bbx0big3cirpzstkdsdj3bzbblw5lm&rid=giphy.gif&ct=g";

    when(giphyClient.search(anyMap()))
        .thenReturn(GiphySearchResource.builder()
            .data(Collections.nCopies(10, GiphyDataResource.builder()
                .images(GiphyImagesResource.builder()
                    .original(GiphyImagesOriginalResource.builder()
                        .url(url)
                        .build())
                    .build())
                .build()))
            .build());

    GiphyService giphyService = new GiphyService(giphyClient);
    String value = giphyService.getUrl(EvaluationTag.RICH);
    assertThat(value).isEqualTo(url);
  }
}
