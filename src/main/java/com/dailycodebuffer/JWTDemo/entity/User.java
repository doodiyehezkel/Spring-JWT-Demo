package com.dailycodebuffer.JWTDemo.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "Users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String email;
    @Column(length = 60)
    private String password;
    private String role;
    private boolean enabled = false;


}
