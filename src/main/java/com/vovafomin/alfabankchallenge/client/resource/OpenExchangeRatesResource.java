package com.vovafomin.alfabankchallenge.client.resource;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpenExchangeRatesResource {

  private String disclaimer;

  private String license;

  private BigDecimal timestamp;

  private String base;

  private Map<String, BigDecimal> rates;
}
