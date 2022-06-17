package com.vovafomin.alfabankchallenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.vovafomin.alfabankchallenge.client.OpenExchangeRatesClient;
import com.vovafomin.alfabankchallenge.client.resource.OpenExchangeRatesResource;

@SpringBootTest
public class OpenExchangeRatesServiceTest {

  @Mock
  private OpenExchangeRatesClient openExchangeRatesClient;

  private OpenExchangeRatesService openExchangeRatesService;

  private static OpenExchangeRatesResource latestRates = OpenExchangeRatesResource.builder()
      .rates(Map.of("EUR", new BigDecimal(0.90), "JPY", new BigDecimal(120))).build();

  private static OpenExchangeRatesResource historicalRates = OpenExchangeRatesResource.builder()
      .rates(Map.of("EUR", new BigDecimal(0.80), "JPY", new BigDecimal(130))).build();

  @BeforeEach
  public void beforeAll() {
    when(openExchangeRatesClient.latest(anyMap())).thenReturn(latestRates);
    when(openExchangeRatesClient.historical(any(), anyMap())).thenReturn(historicalRates);

    openExchangeRatesService = new OpenExchangeRatesService(openExchangeRatesClient);
  }

  @Test
  public void latestReturnsCorrectResource() {
    assertThat(openExchangeRatesService.latest()).isEqualTo(latestRates);
  }

  @Test
  public void historicalReturnsCorrectResource() {
    assertThat(openExchangeRatesService.historical(LocalDate.now())).isEqualTo(historicalRates);
  }
}
