package com.fai.SecurityDemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {
    /*@Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}123")
                .roles("USER").build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}123")
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user,admin);
    }*/
    @Bean
    public UserDetailsManager userDetailsManager( DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select user_id,password,is_active from members where user_id=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
        return userDetailsManager;
    }

    @Bean
    public DefaultSecurityFilterChain http(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(configuration ->{
           configuration.requestMatchers("/pricing/**")
                   .hasRole("ADMIN")
                   .anyRequest()
                   .authenticated();
        }).formLogin(
//                Customizer.withDefaults()
                form->form.loginPage("/loginPage123")
                        .loginProcessingUrl("/signin")
                        .defaultSuccessUrl("/list",true)
                        .permitAll()
        );
//        ).exceptionHandling(
//                configurer -> configurer.accessDeniedPage("/exception/access-denied")
//        );
        return httpSecurity.build();
    }
}


