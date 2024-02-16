package br.com.cpqd.picpay.service;

import br.com.cpqd.picpay.dto.user.UserDto;

public interface FindUserService {
    UserDto findUserById(Long id);
}
