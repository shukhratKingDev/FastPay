package com.pay.fastpay.service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class Main {
    long time=600000000;
    String  key="secret";
    public String generateToken(String username) {

//        Date date=new Date(System.currentTimeMillis());
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .signWith(SignatureAlgorithm.HS512,key)
                .compact();
        return token;
    }

    public boolean validateToken(String token){
       try{
           Jwts.parser()
                   .setSigningKey(key)
                   .parseClaimsJws(token);
           return true;
       }catch (Exception e){
           System.out.println(e.getMessage());
           return false;
       }

    }

    public String getUsername(String token){
       String username=  Jwts.parser()
               .setSigningKey(key).parseClaimsJws(token)
               .getBody().getSubject();
        return username;
    }

//    public static void main(String[] args) {
//        System.out.println(generateToken("username"));
//    }
}
