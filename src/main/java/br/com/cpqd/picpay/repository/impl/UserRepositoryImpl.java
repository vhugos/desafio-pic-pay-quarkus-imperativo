package br.com.cpqd.picpay.repository.impl;

import br.com.cpqd.picpay.domain.user.UserEntity;
import br.com.cpqd.picpay.dto.user.UserDto;
import br.com.cpqd.picpay.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepository<UserEntity> {
    @Override
    public UserEntity findUserById(Long id) {
        try {
            return find("id", id).singleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        try{
           return find("email", email).singleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserEntity findUserByCpf(String cpf) {
        try{
            return find("cpf", cpf).singleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<UserEntity> all = this.listAll();
        return all.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void createUser(UserEntity user) {
        this.persist(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        this.persist(user);
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
