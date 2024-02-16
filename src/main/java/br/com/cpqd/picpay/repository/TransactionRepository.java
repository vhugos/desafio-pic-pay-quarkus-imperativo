package br.com.cpqd.picpay.repository;

import br.com.cpqd.picpay.domain.transaction.TransactionEntity;

public interface TransactionRepository {

    void createTransaction(TransactionEntity entity);
}
