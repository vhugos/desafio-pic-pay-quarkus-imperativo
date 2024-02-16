package br.com.cpqd.picpay.service;

import br.com.cpqd.picpay.dto.user.UserDto;
import jakarta.validation.Valid;

public interface UserServiceValidator {
    void validateDto(@Valid UserDto dto);
}
