package br.com.cpqd.picpay.service.impl;

import br.com.cpqd.picpay.domain.user.UserEntity;
import br.com.cpqd.picpay.domain.user.UserType;
import br.com.cpqd.picpay.dto.user.UserDto;
import br.com.cpqd.picpay.exception.ServerException;
import br.com.cpqd.picpay.repository.UserRepository;
import br.com.cpqd.picpay.service.FindUserService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class FindUserServiceImplTest {


    private UserRepository repository = new UserRepository() {
        @Override
        public UserEntity findUserById(Long id) {
            return createMockUserEntity(id);
        }

        @Override
        public UserEntity findUserByEmail(String email) {
            return null;
        }

        @Override
        public UserEntity findUserByCpf(String cpf) {
            return null;
        }

        @Override
        public List<UserDto> findAll() {
            return null;
        }
    };
    private UserRepository repositoryError = new UserRepository() {
        @Override
        public UserEntity findUserById(Long id) {
            throw new RuntimeException("Error");
        }

        @Override
        public UserEntity findUserByEmail(String email) {
            return null;
        }

        @Override
        public UserEntity findUserByCpf(String cpf) {
            return null;
        }

        @Override
        public List<UserDto> findAll() {
            return null;
        }
    };

    @Test
    @Transactional
    public void testFindUserById() {
        // Given
        Long userId = 1L;

        // When
        FindUserService userService = new FindUserServiceImpl(repository);
        UserDto result = userService.findUserById(userId);

        // Then
        assertNotNull(result);
        assertEquals(userId, result.getId());
        // Add more assertions based on the expected behavior of your method
    }

    @Test
    @Transactional
    public void testFindUserByIdError() {
        // Given
        Long userId = 1L;

        // When
        FindUserService userService = new FindUserServiceImpl(repositoryError);
        assertThrows(ServerException.class, () -> userService.findUserById(1l));
    }

    private UserEntity createMockUserEntity(Long userId) {
        // Create a mock UserEntity for testing
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setUserType(UserType.COMMUN);
        userEntity.setCpf("123456789");
        userEntity.setEmail("test@example.com");
        userEntity.setBalance(new BigDecimal("100.0"));
        userEntity.setFullName("John Doe");
        userEntity.setPassword("password123");
        return userEntity;
    }
}
