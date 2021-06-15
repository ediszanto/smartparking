package com.example.smartparking.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotNull
    @Size(max = 10)
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(max = 10)
    private String lastName;

    @NotNull
    @Size(max = 20, min = 6)
    private String email;

    @NotNull
    @Size(min = 3)
    private String password;

    @NotNull
    @Size(max = 12, min = 10)
    @Pattern(regexp = "[0-9]{10,12}")
    private String phone;

    @NotNull
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Authority> authorities;
}