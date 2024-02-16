package br.com.cpqd.picpay.service.impl;

import br.com.cpqd.picpay.dto.transaction.TransactionDto;
import br.com.cpqd.picpay.service.TransactionServiceValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
@ApplicationScoped

public class TransactionServiceValidatorImpl implements TransactionServiceValidator {
    @Override
    public void validateDto(@Valid TransactionDto dto) {
    }
}
