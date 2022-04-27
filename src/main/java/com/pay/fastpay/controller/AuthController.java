package com.pay.fastpay.controller;

import com.pay.fastpay.entity.ReportDto;
import com.pay.fastpay.service.Main;
import com.pay.fastpay.service.MyAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api")

public class AuthController {


    private MyAuthService myAuthService;

    private Main main;
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;
@Autowired
    public AuthController(MyAuthService myAuthService, Main main, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.myAuthService = myAuthService;
        this.main = main;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginToSystem(@RequestBody ReportDto reportDto) {

        try{
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reportDto.getUsername(),
                   reportDto.getPassword()));
            return ResponseEntity.ok("ok");
        }
        catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Login or password not correct");

        }
    }




}
