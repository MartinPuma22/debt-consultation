package com.demo.poc.entrypoint.exchange.service;

import com.demo.poc.entrypoint.exchange.dto.ExchangeRateResponseDto;

public interface ExchangeRateService {

    ExchangeRateResponseDto generateExchangeRate(String origin, String target);
}
