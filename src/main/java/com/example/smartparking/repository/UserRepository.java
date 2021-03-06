package com.example.smartparking.repository;

import com.example.smartparking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    @Query("select u from User u where u.email=:userName")
    Optional<User> findUserByUsername(String userName);

}
