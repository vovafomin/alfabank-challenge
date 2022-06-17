package com.vovafomin.alfabankchallenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.vovafomin.alfabankchallenge.client.resource.OpenExchangeRatesResource;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EvaluationServiceTest {

  @Mock
  private OpenExchangeRatesService openExchangeRatesService;

  private EvaluationService evaluationService;

  @BeforeEach
  public void beforeEach() {
    evaluationService = new EvaluationService(openExchangeRatesService);

    when(openExchangeRatesService.latest())
        .thenReturn(OpenExchangeRatesResource.builder()
            .rates(Map.of("EUR", new BigDecimal(0.90), "JPY", new BigDecimal(120))).build());

    when(openExchangeRatesService.historical(any()))
        .thenReturn(OpenExchangeRatesResource.builder()
            .rates(Map.of("EUR", new BigDecimal(0.80), "JPY", new BigDecimal(130))).build());
  }

  @Test
  public void getTagReturnsRichWhenCurrencyRateDecreases() {
    assertThat(evaluationService.getTag("JPY", 1)).isEqualTo(EvaluationTag.RICH);
  }

  @Test
  public void getTagReturnsBrokeWhenCurrencyRateIncreases() {
    assertThat(evaluationService.getTag("EUR", 1)).isEqualTo(EvaluationTag.BROKE);
  }

  @Test
  public void getTagThrowsWhenCurrencyIsInvalid() {
    assertThrows(CurrencyNotFoundException.class, () -> evaluationService.getTag("ABC", 1));
  }
}
