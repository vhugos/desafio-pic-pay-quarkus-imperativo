package br.com.cpqd.picpay.dto.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDto {
    private Long id;
    @NotNull(message = "TRANCTION_AMOUNT__NOTBLANK")
    private BigDecimal amount;
    @NotNull(message = "TRANCTION_PAYER__NOTBLANK")
    private Long payerId;
    @NotNull(message = "TRANCTION_PAYEE__NOTBLANK")
    private Long payeeId;
}
