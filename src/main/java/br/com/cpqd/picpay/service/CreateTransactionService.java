package br.com.cpqd.picpay.service;

import br.com.cpqd.picpay.dto.transaction.TransactionDetailDto;
import br.com.cpqd.picpay.dto.transaction.TransactionDto;

public interface CreateTransactionService {
    TransactionDetailDto createTransaction(TransactionDto dto);
}
