package br.com.cpqd.picpay.service.impl;

import br.com.cpqd.picpay.dto.user.UserDto;
import br.com.cpqd.picpay.repository.UserRepository;
import br.com.cpqd.picpay.service.FindAllUsersService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class FindAllUsersServiceImpl implements FindAllUsersService {

    @Inject
    UserRepository repository;
    @Override
    public List<UserDto> findUsersById() {
        return repository.findAll();
    }
}
