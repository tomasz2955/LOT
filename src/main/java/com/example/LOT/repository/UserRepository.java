package com.example.LOT.repository;

import com.example.LOT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword (String email, String password); //to powinno zwracac optionala bo nie wiesz czy coś znajdziesz

    Optional<User> findByEmail (String email);

}
