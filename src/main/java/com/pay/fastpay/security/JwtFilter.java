package com.pay.fastpay.security;

import com.pay.fastpay.service.Main;
import com.pay.fastpay.service.MyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private Main main;
    @Autowired
    MyAuthService myAuthService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      //Getting token from request
        String token = request.getHeader("Authentication");
        System.out.println(token);
        //checking token whether token start with bearer or not.
        if (token!=null&&token.startsWith("Bearer")) {
            //gotten real token without bearer
            token=token.substring(7);
            // checking token. crashed, cracked , not expired.
boolean validateToken= main.validateToken(token);
if (validateToken){
    //getting username
    String username = main.getUsername(token);
    // by using username we make userDetails.
    UserDetails userDetails = myAuthService.loadUserByUsername(username);


    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    System.out.println(SecurityContextHolder.getContext().getAuthentication());
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    System.out.println(SecurityContextHolder.getContext().getAuthentication());

}

        }
        filterChain.doFilter(request,response);


    }
}
