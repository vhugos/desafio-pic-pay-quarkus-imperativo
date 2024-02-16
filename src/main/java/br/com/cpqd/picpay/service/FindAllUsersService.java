package br.com.cpqd.picpay.service;

import br.com.cpqd.picpay.dto.user.UserDto;

import java.util.List;

public interface FindAllUsersService {
    List<UserDto> findUsersById();
}
