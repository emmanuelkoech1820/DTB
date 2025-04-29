package com.dtb.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/v1/profiles/register").permitAll()
                        .anyRequest().permitAll()
                ).formLogin(form -> form.disable())  // Disable form login
                .httpBasic(basic -> basic.disable()); // Disable HTTP Basic Auth popup

        return http.build();

    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/swagger-resources/**",
//                                "/webjars/**"
//                        ).permitAll()  // Allow Swagger access without auth
//                        .anyRequest().authenticated()  // Secure other endpoints
//                )
//                .formLogin(form -> form.disable())  // Disable form login
//                .httpBasic(basic -> basic.disable()); // Disable HTTP Basic Auth popup
//
//        return http.build();
//    }
}
