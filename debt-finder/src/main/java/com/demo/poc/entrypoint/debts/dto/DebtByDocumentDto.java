package com.demo.poc.entrypoint.debts.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebtByDocumentDto implements Serializable {

    private Long id;
    private boolean isPaidOff;
    private double amount;
}
