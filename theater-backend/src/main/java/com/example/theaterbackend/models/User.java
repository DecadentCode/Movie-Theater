package com.example.theaterbackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class User {

    @Id
    @SequenceGenerator(
            name="appuser_sequence",
            sequenceName = "appuser_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "appuser_sequence")
    private Long id;
    private String username;
    private String email;
    private String password;
    private String roles;

}
