package com.demo.poc.entrypoint.debts.service;

import com.demo.poc.entrypoint.debts.dto.*;
import com.demo.poc.entrypoint.debts.entity.DebtEntity;

import java.util.List;

public interface DebtFinderService {

    DebtDto findById(Long id);

    DebtByDocumentDto findByDni(String customerDni);

    List<DebtByCurrency> findByCurrency(String currency);

    List<DebtsByStatusDto> findByPaymentStatus (boolean isPaidOff);

    List<DebtsLeakedDto>findDebtsGreaterThan(double amount);


}
