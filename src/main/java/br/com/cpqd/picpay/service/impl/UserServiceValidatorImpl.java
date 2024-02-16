package br.com.cpqd.picpay.service.impl;

import br.com.cpqd.picpay.domain.user.UserEntity;
import br.com.cpqd.picpay.dto.user.UserDto;
import br.com.cpqd.picpay.exception.ServerException;
import br.com.cpqd.picpay.repository.UserRepository;
import br.com.cpqd.picpay.service.UserServiceValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
@ApplicationScoped
public class UserServiceValidatorImpl implements UserServiceValidator {

    @Inject
    UserRepository userRepository;
    @Override
    public void validateDto(@Valid UserDto dto) {
        UserEntity userByEmail = userRepository.findUserByEmail(dto.getEmail());
        if (userByEmail != null){
            throw new ServerException("USER_EMAIL__ALREADYEXIST",new String[]{dto.getEmail()}, 422);
        }
        UserEntity userByCpf = userRepository.findUserByCpf(dto.getCpf());
        if (userByCpf != null){
            throw new ServerException("USER_CPF__ALREADYEXIST", new String[]{dto.getCpf()}, 422);
        }
    }
}
