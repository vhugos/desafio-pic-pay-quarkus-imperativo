package br.com.cpqd.picpay.service.impl;

import br.com.cpqd.picpay.domain.user.UserEntity;
import br.com.cpqd.picpay.dto.user.UserDto;
import br.com.cpqd.picpay.exception.ServerException;
import br.com.cpqd.picpay.service.CreateUserService;
import br.com.cpqd.picpay.service.UserServiceValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;

@ApplicationScoped
public class CreateUserServiceImpl implements CreateUserService {

    @Inject
    UserServiceValidator userServiceValidator;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        validate(userDto);
        UserEntity userEntity = new UserEntity();
        userEntity.setBalance(userDto.getBalance());
        userEntity.setUserType(userDto.getUserType());
        userEntity.setCpf(userDto.getCpf());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFullName(userDto.getFullName());
        userEntity.persist();
        userDto.setId(userEntity.getId());
        return userDto;
    }

    private void validate(UserDto dto){
        try {
            userServiceValidator.validateDto(dto);
        } catch (ConstraintViolationException e){
            throw new ServerException(e.getConstraintViolations());
        }
    }
}
