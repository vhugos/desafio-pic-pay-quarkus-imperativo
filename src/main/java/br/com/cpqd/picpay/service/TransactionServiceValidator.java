package br.com.cpqd.picpay.service;

import br.com.cpqd.picpay.dto.transaction.TransactionDto;
import jakarta.validation.Valid;

public interface TransactionServiceValidator {
    void validateDto(@Valid TransactionDto dto);
}
