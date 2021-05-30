package com.example.smartparking.repository;

import com.example.smartparking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("Select u from User u where u.id=:id")
    Optional<User> findById(Long id);

    @Query("delete from User u where u.id=:id")
    void deleteById(Long id);
}
