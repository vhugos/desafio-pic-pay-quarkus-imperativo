package br.com.cpqd.picpay.service;

import br.com.cpqd.picpay.domain.user.UserEntity;

import java.math.BigDecimal;

public interface PayerValidatorService {
    void validate(UserEntity payer, BigDecimal amount);
}
