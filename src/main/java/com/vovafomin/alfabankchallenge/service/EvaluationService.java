package com.vovafomin.alfabankchallenge.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.vovafomin.alfabankchallenge.client.resource.OpenExchangeRatesResource;

@Service
public class EvaluationService {

  private OpenExchangeRatesService openExchangeRatesService;

  public EvaluationService(OpenExchangeRatesService openExchangeRatesService) {
    this.openExchangeRatesService = openExchangeRatesService;
  }

  public String getTag(String code, int minusDays) {
    OpenExchangeRatesResource latestRates = openExchangeRatesService.latest();
    OpenExchangeRatesResource yesterdayRates = openExchangeRatesService
        .historical(LocalDate.now().minusDays(minusDays));

    if (!yesterdayRates.getRates().containsKey((code)) || !latestRates.getRates().containsKey((code))) {
      throw new CurrencyNotFoundException();
    }

    BigDecimal yesterdayValue = yesterdayRates.getRates().get(code);
    BigDecimal latestValue = latestRates.getRates().get(code);

    if (latestValue.compareTo(yesterdayValue) <= 0) {
      return EvaluationTag.RICH;
    } else {
      return EvaluationTag.BROKE;
    }
  }
}
