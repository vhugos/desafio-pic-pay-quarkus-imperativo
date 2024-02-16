package br.com.cpqd.picpay.service.impl;

import br.com.cpqd.picpay.domain.user.UserEntity;
import br.com.cpqd.picpay.dto.user.UserDto;
import br.com.cpqd.picpay.exception.ServerException;
import br.com.cpqd.picpay.repository.UserRepository;
import br.com.cpqd.picpay.service.FindUserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

@ApplicationScoped
@AllArgsConstructor
@NoArgsConstructor
public class FindUserServiceImpl implements FindUserService {

    @Inject
    UserRepository repository;
    @Override
    public UserDto findUserById(Long id) {
        try {
            UserEntity byId = repository.findUserById(id);
            UserDto dto = new UserDto();
            dto.setId(byId.getId());
            dto.setUserType(byId.getUserType());
            dto.setCpf(byId.getCpf());
            dto.setEmail(byId.getEmail());
            dto.setBalance(byId.getBalance());
            dto.setFullName(byId.getFullName());
            dto.setPassword(byId.getPassword());
            return dto;
        } catch (Exception e){
            throw new ServerException("USER__NOTFOUND", HttpStatus.SC_NOT_FOUND);
        }
    }
}
