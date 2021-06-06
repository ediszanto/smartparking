package com.example.smartparking.controller;

import com.example.smartparking.model.User;
import com.example.smartparking.service.UserService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/smartparking")
public class UserContoller {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@Validated @RequestBody User user){
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User userByEmail = userService.getUserByEmail(email);
        return new ResponseEntity<>(userByEmail, HttpStatus.OK);
    }

    @GetMapping("/user/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User userById = userService.getUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<User> updateUserDetails(@PathVariable Long id, @RequestBody User userUpdates){
        User userUpdate = userService.updateUserDetails(id, userUpdates);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }


}
