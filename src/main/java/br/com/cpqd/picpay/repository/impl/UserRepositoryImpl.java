package br.com.cpqd.picpay.repository.impl;

import br.com.cpqd.picpay.domain.user.UserEntity;
import br.com.cpqd.picpay.dto.user.UserDto;
import br.com.cpqd.picpay.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {
    @Override
    public UserEntity findUserById(Long id) {
        try {
            return UserEntity.find("id", id).singleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        try{
           return UserEntity.find("email", email).singleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserEntity findUserByCpf(String cpf) {
        try{
            return UserEntity.find("cpf", cpf).singleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> all = UserEntity.findAll().list();
        return all.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserDto convertToDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUserType(entity.getUserType());
        dto.setCpf(entity.getCpf());
        dto.setEmail(entity.getEmail());
        dto.setBalance(entity.getBalance());
        dto.setFullName(entity.getFullName());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
