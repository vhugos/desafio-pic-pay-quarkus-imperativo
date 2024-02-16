package br.com.cpqd.picpay.repository;

import br.com.cpqd.picpay.domain.user.UserEntity;
import br.com.cpqd.picpay.dto.user.UserDto;

import java.util.List;

public interface UserRepository {

    UserEntity findUserById(Long id);

    UserEntity findUserByEmail(String email);

    UserEntity findUserByCpf(String cpf);

    List<UserDto> findAll();

}
