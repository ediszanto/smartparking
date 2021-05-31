//package com.example.smartparking.security;
//
//import com.example.smartparking.model.User;
//import com.example.smartparking.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class JpaUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findUserByUsername(username);
//        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Username not found")); // il scriu asa pt ca userOptional cere optional nu User
//        return new SecurityUser(user);
//    }
//}
