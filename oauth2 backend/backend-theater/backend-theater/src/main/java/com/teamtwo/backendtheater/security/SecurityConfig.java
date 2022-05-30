package com.teamtwo.backendtheater.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure (HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/edit").permitAll()
                .antMatchers("/ticket").permitAll()
                .antMatchers("/purchase").permitAll()
                .anyRequest().authenticated()
                .and().oauth2Login();
    }

}
