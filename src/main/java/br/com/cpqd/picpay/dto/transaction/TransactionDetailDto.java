package br.com.cpqd.picpay.dto.transaction;

import br.com.cpqd.picpay.dto.user.UserDto;
import lombok.Data;

@Data
public class TransactionDetailDto {
    private Long id;
    private UserDto payee;
    private UserDto payer;
}
