package br.com.cpqd.picpay.domain.transaction;

import br.com.cpqd.picpay.domain.user.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransactionEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private UserEntity payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private UserEntity payee;
    @Column(name = "TRANSACTION_DATE")

    private LocalDateTime transactionDate;
}
