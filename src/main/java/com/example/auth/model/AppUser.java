package com.example.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AppUser {
    private Integer id;
    private String username, password;
    private String role;
}
