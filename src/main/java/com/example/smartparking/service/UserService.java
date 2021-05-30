package com.example.smartparking.service;

import com.example.smartparking.model.User;
import javassist.NotFoundException;

public interface UserService {

    User addNewUser(User user);

    User getUserByEmail(String email) throws NotFoundException;

    User getUserById(Long userId) throws NotFoundException;

    void deleteUserById(Long id);
}
