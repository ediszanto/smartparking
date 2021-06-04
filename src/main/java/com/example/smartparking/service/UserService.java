package com.example.smartparking.service;

import com.example.smartparking.model.User;
import javassist.NotFoundException;

public interface UserService {

    User addNewUser(User user);

    User getUserByEmail(String email);

    User getUserById(Long userId);

    void deleteUserById(Long id);

    User updateUserDetails(Long id, User userUpdates);
}
