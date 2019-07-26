package com.beansbeans.moneyapp.Repositories;

import com.beansbeans.moneyapp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserNameAndPasswordHash(String email, String password);

    User findUserByUserNameIs(String userName);
}
