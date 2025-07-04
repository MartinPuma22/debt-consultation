package com.demo.poc.entrypoint.debts.repository;

import com.demo.poc.commons.repository.csv.CsvRepository;
import com.demo.poc.entrypoint.debts.entity.DebtEntity;

import java.util.List;

public class DebtCsvRepositoryImpl extends CsvRepository<DebtEntity> implements DebtCsvRepository {

    private static final String FILE_PATH = "/debts.csv";

    public DebtCsvRepositoryImpl() {
        super(FILE_PATH, DebtEntity.class);
    }

    @Override
    public DebtEntity findByDni(String customerDni) {
        return this.findAll().stream()
                .filter(debtEntity -> debtEntity.getCustomerDni().equalsIgnoreCase(customerDni))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Customer Not found"));
    }

    @Override
    public List<DebtEntity> findByCurrency(String currency) {
        return this.findAll().stream()
                .filter(debtEntity -> debtEntity.getCurrency().equalsIgnoreCase(String.valueOf(currency)))
                .toList();
    }




}
