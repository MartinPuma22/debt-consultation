package com.demo.poc.entrypoint.debts.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebtDto implements Serializable {


    private boolean isPaidOff;
    private double amount;
    private String customerDni;
}
