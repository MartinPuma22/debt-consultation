package com.demo.poc.entrypoint.debts.service;

import com.demo.poc.entrypoint.debts.dto.DebtByCurrency;
import com.demo.poc.entrypoint.debts.dto.DebtByDocumentDto;
import com.demo.poc.entrypoint.debts.dto.DebtDto;

import java.util.List;

public interface DebtFinderService {

    DebtDto findById(Long id);


    DebtByDocumentDto findByDni(String customerDni);

    List<DebtByCurrency> findByCurrency(String currency);


}
