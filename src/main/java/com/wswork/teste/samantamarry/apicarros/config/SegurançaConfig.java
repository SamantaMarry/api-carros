package com.wswork.teste.samantamarry.apicarros.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
@Configuration
public class SegurançaConfig extends WebSecurityConfigurerAdapter {

    @Value("${seguranca.config.username}")
    private String username;
    @Value("${seguranca.config.password}")
    private String password;
    @Value("${seguranca.config.roles}")
    private String roles;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser(username)
                .password(password)
                .roles(roles);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
