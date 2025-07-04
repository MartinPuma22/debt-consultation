package com.demo.poc.entrypoint.debts.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DebtByCurrency implements Serializable {

    private double amount;
    private boolean isPaidOff;
    private String customerDni;

}
