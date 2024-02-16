package br.com.cpqd.picpay.service.impl;

import br.com.cpqd.picpay.domain.user.UserEntity;
import br.com.cpqd.picpay.domain.user.UserType;
import br.com.cpqd.picpay.exception.ServerException;
import br.com.cpqd.picpay.service.PayerValidatorService;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.math.BigDecimal;
@ApplicationScoped
public class PayerValidatorServiceImpl implements PayerValidatorService {
    private static final Logger LOGGER = Logger.getLogger(PayerValidatorServiceImpl.class.getName());

    @Override
    public void validate(UserEntity payer, BigDecimal amount) {
        LOGGER.info(payer);
        if (payer == null) {
            throw new ServerException("USER_PAYER_NOTFOUND", HttpStatus.SC_NOT_FOUND);
        }
        if (!payer.getUserType().equals(UserType.MERCHANT)) {
            if (payer.getUserType().equals(UserType.MERCHANT)) {
                throw new ServerException("TRANCTION_PAYER__INVALID", HttpStatus.SC_UNPROCESSABLE_ENTITY);
            }

            if (payer.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
                throw new ServerException("TRANCTION_PAYER__WITHOUT_BALANCE", HttpStatus.SC_UNPROCESSABLE_ENTITY);
            }

            if (payer.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0){
                throw new ServerException("TRANCTION_PAYER__INSUFFICIENT_BALANCE", HttpStatus.SC_UNPROCESSABLE_ENTITY);
            }
        }
    }
}
