package br.com.cpqd.picpay.service.impl;

import br.com.cpqd.picpay.domain.transaction.TransactionEntity;
import br.com.cpqd.picpay.domain.user.UserEntity;
import br.com.cpqd.picpay.dto.transaction.TransactionDetailDto;
import br.com.cpqd.picpay.dto.transaction.TransactionDto;
import br.com.cpqd.picpay.dto.user.UserDto;
import br.com.cpqd.picpay.exception.ServerException;
import br.com.cpqd.picpay.repository.UserRepository;
import br.com.cpqd.picpay.service.CreateTransactionService;
import br.com.cpqd.picpay.service.FindUserService;
import br.com.cpqd.picpay.service.PayerValidatorService;
import br.com.cpqd.picpay.service.TransactionServiceValidator;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;

@ApplicationScoped
public class CreateTransactionServiceImpl implements CreateTransactionService {
    private static final Logger LOGGER = Logger.getLogger(CreateTransactionServiceImpl.class.getName());

    @Inject
    TransactionServiceValidator transactionServiceValidator;

    @Inject
    FindUserService findUserService;

    @Inject
    PayerValidatorService payerValidatorService;
    @Inject
    UserRepository repository;

    @Override
    @Transactional
    public TransactionDetailDto createTransaction(TransactionDto dto) {
        TransactionDetailDto transactionDetailDto = new TransactionDetailDto();
        validate(dto);
        TransactionEntity entity = new TransactionEntity();
        entity.setAmount(dto.getAmount());

        UserEntity payee = repository.findUserById(dto.getPayeeId());
        if (payee == null) {
            throw new ServerException("USER_PAYEE_NOTFOUND", HttpStatus.SC_NOT_FOUND);
        }

        UserEntity payer = repository.findUserById(dto.getPayerId());
        payerValidatorService.validate(payer, dto.getAmount());
        try {
            payee.setBalance(payee.getBalance().add(dto.getAmount()));
            payer.setBalance(payer.getBalance().subtract(dto.getAmount()));

            payee.persist();
            payer.persist();

            entity.setPayee(payee);
            entity.setPayer(payer);
            entity.setTransactionDate(LocalDateTime.now());

            entity.persist();
            transactionDetailDto.setId(entity.getId().longValue());
            transactionDetailDto.setPayee(convertToDto(payee));
            transactionDetailDto.setPayer(convertToDto(payer));
            return transactionDetailDto;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServerException("ERR__INTERNAL_SERVER_ERROR", 500);
        }
    }

    private void validate(TransactionDto dto) {
        try {
            transactionServiceValidator.validateDto(dto);
        } catch (ConstraintViolationException e) {
            throw new ServerException(e.getConstraintViolations());
        }
    }

    private UserDto convertToDto(UserEntity userEntity) {
        UserDto dto = new UserDto();
        dto.setId(userEntity.getId());
        dto.setUserType(userEntity.getUserType());
        dto.setCpf(userEntity.getCpf());
        dto.setEmail(userEntity.getEmail());
        dto.setBalance(userEntity.getBalance());
        dto.setFullName(userEntity.getFullName());
        dto.setPassword(userEntity.getPassword());
        return dto;
    }
}
