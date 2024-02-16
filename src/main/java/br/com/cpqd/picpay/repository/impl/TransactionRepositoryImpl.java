package br.com.cpqd.picpay.repository.impl;

import br.com.cpqd.picpay.domain.transaction.TransactionEntity;
import br.com.cpqd.picpay.repository.TransactionRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionRepositoryImpl implements TransactionRepository, PanacheRepository<TransactionEntity> {
    @Override
    public void createTransaction(TransactionEntity entity) {
        this.persist(entity);
    }
}
