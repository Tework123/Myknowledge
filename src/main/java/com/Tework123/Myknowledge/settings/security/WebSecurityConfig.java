package com.Tework123.Myknowledge.settings.security;

import com.Tework123.Myknowledge.services.user.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers(HttpMethod.GET,
                                        "admin/**",
                                        "/users",
                                        "/cars",
                                        "/cars/{id}",
                                        "/users/create",
                                        "/users/{id}/edit"

                                ).authenticated()
                                .requestMatchers(HttpMethod.GET,
                                        "/users"
                                ).authenticated()
//                                .requestMatchers(
//                                        "/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,
                                        "/signin",
                                        "/signup"
                                ).permitAll()
                                .requestMatchers(HttpMethod.POST,
                                        "/cars/create"
                                ).authenticated()
                                .requestMatchers(HttpMethod.PATCH,
                                        "/users/{id}"

                                ).permitAll()

                                .requestMatchers(HttpMethod.DELETE,
                                        "/post/{id}",
                                        "/profile/delete"
                                ).authenticated()

                                .anyRequest().permitAll()
//                        формочки все равно возвращает, даже без авторизации

                ).rememberMe((remember) -> remember
//              куки устанавливаются, после дропа сервера не слетают, key обязателен
                                .alwaysRemember(true)
                                .tokenValiditySeconds(60 * 60 * 24 * 365)
                                .key("mySecret")
//                ).formLogin((form) -> form
//                        .loginPage("/signin")
//                        .defaultSuccessUrl("/users", true)
//                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();

    }

//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
