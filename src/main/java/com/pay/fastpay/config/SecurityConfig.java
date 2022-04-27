package com.pay.fastpay.config;

import com.pay.fastpay.security.JwtFilter;
import com.pay.fastpay.service.MyAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    private final MyAuthService myAuthService;
    private final JwtFilter jwtFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/","/api/login","/api/auth").permitAll()
        .anyRequest().authenticated();
//
http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//spring securityga sessiyaga ushlay olmasligi buyurmoqda.
http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.userDetailsService(myAuthService);
    }
@Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
}
}
