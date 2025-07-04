package com.demo.poc.entrypoint.exchange.service;

import com.demo.poc.entrypoint.exchange.dto.ExchangeRateResponseDto;

import java.text.DecimalFormat;
import java.util.Random;

public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Override
    public ExchangeRateResponseDto generateExchangeRate(String origin, String target) {
        double rate = 1.0 + new Random().nextDouble() * 4.0;
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        String rateFormatted = numberFormat.format(rate);
        return ExchangeRateResponseDto.builder()
                .rate(rateFormatted)
                .origin(origin)
                .target(target)
                .build();

    }
}
