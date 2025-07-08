package com.demo.poc.entrypoint.debts.service;

import com.demo.poc.entrypoint.debts.dto.*;
import com.demo.poc.entrypoint.debts.entity.DebtEntity;
import com.demo.poc.entrypoint.debts.repository.DebtCsvRepositoryImpl;
import com.google.inject.Inject;

import java.util.List;

public class DebtFinderServiceImpl implements DebtFinderService {



    private final DebtCsvRepositoryImpl repository;

    @Inject
    DebtFinderServiceImpl(DebtCsvRepositoryImpl debtCsvRepository){
        this.repository = debtCsvRepository;
    }
    @Override
    public DebtDto findById(Long id) {
        DebtEntity entity = repository.findById(id);

        return DebtDto.builder()
                .amount(entity.getAmount())
                .customerDni(entity.getCustomerDni())
                .isPaidOff(entity.isPaidOff())
                .build();
    }

    @Override
    public DebtByDocumentDto findByDni(String customerDni) {
        DebtEntity entity = repository.findByDni(customerDni);
        return DebtByDocumentDto.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .isPaidOff(entity.isPaidOff())
                .build();
    }

    @Override
    public List<DebtByCurrency> findByCurrency(String currency) {
        List<DebtEntity> entities = repository.findByCurrency(currency);
        return entities.stream()
                .map(debtEntity -> DebtByCurrency.builder()
                        .isPaidOff(debtEntity.isPaidOff())
                        .customerDni(debtEntity.getCustomerDni())
                        .amount(debtEntity.getAmount())
                        .build()).toList();
    }

    @Override
    public List<DebtsByStatusDto> findByPaymentStatus(boolean isPaidOff) {
        List<DebtEntity> entities = repository.findByPaymentStatus(isPaidOff);
        return entities.stream()
                .map(debtEntity -> DebtsByStatusDto.builder()
                        .customerDni(debtEntity.getCustomerDni())
                        .amount(debtEntity.getAmount())
                        .build()).toList();
    }

    @Override
    public List<DebtsLeakedDto> findDebtsGreaterThan(double amount) {
        List<DebtEntity> entities = repository.findDebtsGreaterThan(amount);
        return entities.stream()
                .map(debtEntity -> DebtsLeakedDto.builder()
                        .amount(debtEntity.getAmount())
                        .currency(debtEntity.getCurrency())
                        .customerDni(debtEntity.getCustomerDni())
                        .isPaidOff(debtEntity.isPaidOff())
                        .build())
                .toList();
    }
}
