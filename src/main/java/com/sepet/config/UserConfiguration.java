package com.sepet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class UserConfiguration {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user1 = User.builder()
                .username("umut")
                .password(bCryptPasswordEncoder().encode("laz53"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("apo")
                .password(bCryptPasswordEncoder().encode("kurt36"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        security
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x.requestMatchers("/product/**").permitAll())
                .authorizeHttpRequests(x -> x.requestMatchers("/User/**").hasRole("ADMIN"))
                .authorizeHttpRequests(x -> x.requestMatchers("/User/{id}/**").hasRole("ADMIN"))
                .authorizeHttpRequests(x -> x.requestMatchers("/product/{id}/**").hasRole("ADMIN"))
                .authorizeHttpRequests(x -> x.requestMatchers("/produc/**").hasRole("ADMIN"))
                .authorizeHttpRequests(x -> x.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return security.build();
    }

}
