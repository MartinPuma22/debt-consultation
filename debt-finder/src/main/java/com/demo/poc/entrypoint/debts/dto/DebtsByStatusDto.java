package com.demo.poc.entrypoint.debts.dto;

import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DebtsByStatusDto implements Serializable {

    private String customerDni;
    private double amount;
}
