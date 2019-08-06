package com.beansbeans.moneyapp.Repositories;

import com.beansbeans.moneyapp.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Iterable<Transaction> findAllByUserId(Long userId);

    Iterable<Transaction> findAllByFromAccountId(Long accountId);

    Iterable<Transaction> findAllByToAccountId(Long accountId);
}
