package br.com.cpqd.picpay.service.impl;

import br.com.cpqd.picpay.domain.transaction.TransactionEntity;
import br.com.cpqd.picpay.domain.user.UserEntity;
import br.com.cpqd.picpay.domain.user.UserType;
import br.com.cpqd.picpay.dto.transaction.TransactionDetailDto;
import br.com.cpqd.picpay.dto.transaction.TransactionDto;
import br.com.cpqd.picpay.dto.user.UserDto;
import br.com.cpqd.picpay.exception.ServerException;
import br.com.cpqd.picpay.repository.TransactionRepository;
import br.com.cpqd.picpay.repository.UserRepository;
import br.com.cpqd.picpay.service.PayerValidatorService;
import br.com.cpqd.picpay.service.TransactionServiceValidator;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class CreateTransactionServiceImplTest {
    private static final Logger LOGGER = Logger.getLogger(CreateTransactionServiceImplTest.class.getName());

    @Inject
    TransactionServiceValidator transactionServiceValidator;


    @Test
    @Transactional
    public void createTransactionSucess() {

        PayerValidatorService service = new PayerValidatorService() {
            @Override
            public void validate(UserEntity payer, BigDecimal amount) {

            }
        };

        UserRepository userRepository = new UserRepository() {
            @Override
            public UserEntity findUserById(Long id) {

                if (id.equals(1l)){
                    UserEntity user = new UserEntity();
                    user.setUserType(UserType.COMMUN);
                    user.setId(1l);
                    user.setCpf("111111");
                    user.setEmail("silvavho@gmail.com");
                    user.setBalance(new BigDecimal(100));
                    user.setPassword("1111");
                    user.setFullName("Vitor");
                    return user;
                } else if (id.equals(2l)){
                    UserEntity user = new UserEntity();
                    user.setUserType(UserType.MERCHANT);
                    user.setId(2l);
                    user.setCpf("22222");
                    user.setEmail("test@gmail.com");
                    user.setBalance(new BigDecimal(100));
                    user.setPassword("2222");
                    user.setFullName("Teste");
                    return user;
                }

                return null;
            }

            @Override
            public UserEntity findUserByEmail(String email) {
                return null;
            }

            @Override
            public UserEntity findUserByCpf(String cpf) {
                return null;
            }

            @Override
            public List<UserDto> findAllUsers() {
                return null;
            }

            @Override
            public void createUser(UserEntity user) {

            }

            @Override
            public void updateUser(UserEntity user) {

            }
        };

        TransactionRepository transactionRepository = new TransactionRepository() {
            @Override
            public void createTransaction(TransactionEntity entity) {
                entity.setId(1l);
            }
        };

        CreateTransactionServiceImpl createTransactionService
                = new CreateTransactionServiceImpl(transactionServiceValidator,
                service,
                userRepository,
                transactionRepository
        );
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setPayeeId(2l);
        transactionDto.setPayerId(1l);
        transactionDto.setAmount(new BigDecimal("100.00"));
        TransactionDetailDto transaction = createTransactionService.createTransaction(transactionDto);
        LOGGER.info(transaction);
        assertEquals(transaction.getPayer().getBalance(), new BigDecimal("0.00"));
        assertEquals(transaction.getPayee().getBalance(), new BigDecimal("200.00"));
        assertNotNull(transaction);

    }

    @Test
    @Transactional
    public void createTransactionErrorUser() {

        PayerValidatorService service = new PayerValidatorService() {
            @Override
            public void validate(UserEntity payer, BigDecimal amount) {

            }
        };

        UserRepository userRepository = new UserRepository() {
            @Override
            public UserEntity findUserById(Long id) {
                return null;
            }

            @Override
            public UserEntity findUserByEmail(String email) {
                return null;
            }

            @Override
            public UserEntity findUserByCpf(String cpf) {
                return null;
            }

            @Override
            public List<UserDto> findAllUsers() {
                return null;
            }

            @Override
            public void createUser(UserEntity user) {

            }

            @Override
            public void updateUser(UserEntity user) {

            }
        };

        TransactionRepository transactionRepository = new TransactionRepository() {
            @Override
            public void createTransaction(TransactionEntity entity) {
                entity.setId(1l);
            }
        };

        CreateTransactionServiceImpl createTransactionService
                = new CreateTransactionServiceImpl(transactionServiceValidator,
                service,
                userRepository,
                transactionRepository
        );
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setPayeeId(2l);
        transactionDto.setPayerId(1l);
        transactionDto.setAmount(new BigDecimal("100.00"));
        assertThrows(ServerException.class, () -> createTransactionService.createTransaction(transactionDto));
    }
}
