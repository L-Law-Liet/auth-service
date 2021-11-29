package com.example.auth.controller;

import com.example.auth.config.JwtConfig;
import com.example.auth.model.AppUser;
import com.example.auth.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth/user")
public class UsersController {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    JwtConfig jwtConfig;

    @GetMapping("")
    public ResponseEntity listAllHeaders(
            @RequestHeader Map<String, String> headers) {
//        return ResponseEntity.ok().body(headers.get("authorization"));
        System.out.println(headers.get("authorization").substring(7));
        Claims body = Jwts.parser()
                .setSigningKey(jwtConfig.getSecret().getBytes())
                .parseClaimsJws(headers.get("authorization").substring(7))
                .getBody();
        System.out.println(body);
        return ResponseEntity.ok().body(((AppUser) userDetailsServiceImpl.loadUserByUsername(body.getSubject())).getId());
    }
}
