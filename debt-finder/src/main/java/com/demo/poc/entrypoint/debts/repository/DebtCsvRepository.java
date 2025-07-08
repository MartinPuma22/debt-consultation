package com.demo.poc.entrypoint.debts.repository;

import com.demo.poc.entrypoint.debts.entity.DebtEntity;

import java.util.List;

public interface DebtCsvRepository {

    DebtEntity findByDni(String customerDni);

    List<DebtEntity> findByCurrency(String currency);

    List<DebtEntity> findByPaymentStatus (boolean isPaidOff );

    List<DebtEntity>findDebtsGreaterThan(double amount);
}
