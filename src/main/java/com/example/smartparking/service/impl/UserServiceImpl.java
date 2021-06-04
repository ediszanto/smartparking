package com.example.smartparking.service.impl;


import com.example.smartparking.model.Authority;
import com.example.smartparking.model.User;
import com.example.smartparking.repository.UserRepository;
import com.example.smartparking.service.UserService;
import com.example.smartparking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User addNewUser(User user) {
        boolean emailExists = userRepository.findByEmail(user.getEmail()).isPresent();
        boolean phoneExists = userRepository.findByEmail(user.getPhone()).isPresent();
        if(emailExists){
            throw new IllegalStateException("This email is attached to another account. Please use another email address");
        }
        if(phoneExists){
            throw new IllegalStateException("This phone is attached to another account. Please use another phone number");
        }
        Authority authority = new Authority();
        authority.setName("CLIENT");
        user.setAuthorities(Collections.singletonList(authority));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public User getUserById(Long userId) throws NotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public void deleteUserById(Long id) throws NotFoundException {
        User userToDelete = getUserById(id);
        userRepository.delete(userToDelete);
    }

    @Override
    public User updateUserDetails(Long id, User userUpdates) {
        User user = userRepository.getById(id);

        user.setPassword(Optional.ofNullable(passwordEncoder.encode(userUpdates.getPassword())).orElse(user.getPassword()));
        user.setEmail(Optional.ofNullable(userUpdates.getEmail()).orElse(user.getEmail()));
        user.setFirstName(Optional.ofNullable(userUpdates.getFirstName()).orElse(user.getFirstName()));
        user.setLastName(Optional.ofNullable(userUpdates.getLastName()).orElse(user.getLastName()));
        user.setPhone(Optional.ofNullable(userUpdates.getPhone()).orElse(user.getPhone()));

        return userRepository.save(user);
    }
}
