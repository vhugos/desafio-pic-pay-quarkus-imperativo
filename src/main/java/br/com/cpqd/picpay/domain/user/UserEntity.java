package br.com.cpqd.picpay.domain.user;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class UserEntity {

    @Id
    @GeneratedValue()
    @Column(name = "ID")
    private Long id;
    @Column(name = "FULLNAME")
    private String fullName;
    @Column(name = "CPF", unique = true)
    private String cpf;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "USER_TYPE")
    private UserType userType;
    private BigDecimal balance;

}
