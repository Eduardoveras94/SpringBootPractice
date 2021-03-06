package com.pucmm.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Eduardo veras on 05-Oct-16.
 */
@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login") //indicando la ruta que estaremos utilizando.
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Cargando los usuarios en memoria.
        auth.inMemoryAuthentication()
                .withUser("vacax")
                .password("1234")
                .roles("ADMIN","USER")
                .and()
                .withUser("usuario")
                .password("1234")
                .roles("USER");
    }




}