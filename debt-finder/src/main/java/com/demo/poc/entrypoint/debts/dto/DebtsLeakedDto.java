package com.demo.poc.entrypoint.debts.dto;

import java.io.Serializable;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebtsLeakedDto implements Serializable {

    private String customerDni;
    private double amount;
    private boolean isPaidOff;
    private String currency;
}
