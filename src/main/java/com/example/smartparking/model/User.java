package com.example.smartparking.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "first_name")
    @Pattern(value = "[a-zA-z]{1,15}")
    private String firstName;

    @Column(name = "last_name")
    @Pattern(value = "[a-zA-z]{1,15}")
    private String lastName;


    @Pattern(value = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;


    private String password;

//    @NotNull
    @Pattern(value = "[0-9]{10,13}")
    private String phone;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Authority> authorities;
}