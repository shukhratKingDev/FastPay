package com.pay.fastpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {
    private PasswordEncoder passwordEncoder;
    private PaymentService paymentService;
@Autowired
    public MyAuthService(@Lazy PasswordEncoder passwordEncoder, PaymentService paymentService) {
        this.passwordEncoder = passwordEncoder;
        this.paymentService = paymentService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User>userList=new ArrayList<>(Arrays.asList(
                new User("shukhratjon",passwordEncoder.encode("1201"),new ArrayList<>()),
                new User("user",passwordEncoder.encode("password"),new ArrayList<>())
        ));
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User does not exists");
    }



}
