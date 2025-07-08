package com.demo.poc.entrypoint.debts.entity;

import com.demo.poc.commons.repository.Entity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebtEntity extends Entity {

    private String  createdAt;
    private String updateAt;
    private boolean isPaidOff;
    private double amount;
    private String currency;
    private String customerDni;
}
