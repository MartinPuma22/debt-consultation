package com.demo.poc.entrypoint.exchange.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeRateResponseDto implements Serializable {

    private String origin;
    private String target;
    private String rate;

}
