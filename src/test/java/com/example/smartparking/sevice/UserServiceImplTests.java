package com.example.smartparking.sevice;

import com.example.smartparking.exceptions.NotFoundException;
import com.example.smartparking.model.Authority;
import com.example.smartparking.model.User;
import com.example.smartparking.repository.UserRepository;
import com.example.smartparking.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @Disabled
    public void saveUserTest(){
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<Authority> authorities = new ArrayList<>();
        Authority authority = new Authority();
        authority.setName("CLIENT");
        authorities.add(authority);

        User user = new User();
        user.setFirstName("Ion");
        user.setLastName("Ionescu");
        user.setPhone("0755223223");
        user.setEmail("ion@gmail.com");
        user.setPassword("1234");
        user.setAuthorities(authorities);

//      TODO gives NullPointerException from PasswordEncoder Bean
        userService.saveUser(user);

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(argumentCaptor.capture());

        User dbUser = argumentCaptor.getValue();
        System.out.println(dbUser.getPassword());
        assertNotNull(dbUser);
        assertTrue(user.getEmail().equals(dbUser.getEmail()));
        assertTrue(user.getPhone().equals(dbUser.getPhone()));
        assertTrue(user.getFirstName().equals(dbUser.getFirstName()));
        assertTrue(user.getLastName().equals(dbUser.getLastName()));
        assertTrue(user.getAuthorities().equals(dbUser.getAuthorities()));
    }

    @Test
    public void testGetUserByEmail(){
        String email = "ion@gmail.com";

        List<Authority> authorities = new ArrayList<>();
        Authority authority = new Authority();
        authority.setName("CLIENT");
        authorities.add(authority);

        User dbUser = new User();
        dbUser.setFirstName("Ion");
        dbUser.setLastName("Ionescu");
        dbUser.setPhone("0755223223");
        dbUser.setEmail("ion@gmail.com");
        dbUser.setPassword("1234");
        dbUser.setAuthorities(authorities);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(dbUser));

        User result = userService.getUserByEmail(email);
        assertNotNull(result);
        assertEquals(dbUser.getEmail(), result.getEmail());
        assertEquals(dbUser.getFirstName(), result.getFirstName());
    }

    @Test
    public void testGetUserByEmailFail(){
        String email = "ion@gmail.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(null));
        assertThrows(NotFoundException.class, () -> userService.getUserByEmail(email));
    }

    @Test
    void testGetUserById(){
        Long id = 1L;
        User dbUser = new User();
        dbUser.setId(1L);

        when(userRepository.findById(id)).thenReturn(Optional.ofNullable(dbUser));
        User result = userService.getUserById(id);
        assertNotNull(result);
        assertTrue(result.getId().equals(id));
    }

    @Test
    void testGetUserByIdFail(){
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.ofNullable(null));
        assertThrows(NotFoundException.class, () -> userService.getUserById(id));
    }
}
