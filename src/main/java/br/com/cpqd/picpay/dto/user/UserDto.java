package br.com.cpqd.picpay.dto.user;

import br.com.cpqd.picpay.domain.user.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class UserDto {
    private Long id;
    @NotBlank(message = "USER_FULLNAME__NOTBLANK")
    private String fullName;
    @NotBlank(message = "USER_CPF__NOTBLANK")
    private String cpf;
    @NotBlank(message = "USER_EMAIL__NOTBLANK")
    private String email;
    @NotBlank(message = "USER_PASSWORD__NOTBLANK")
    private String password;
    @NotNull(message = "USER_USERTYPE__NOTBLANK")
    private UserType userType;
    @NotNull(message = "USER_BALANCE__NOTBLANK")
    private BigDecimal balance;
}
