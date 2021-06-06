package com.example.smartparking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table( name = "authorities")
@Entity
@ToString
public class Authority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
}
