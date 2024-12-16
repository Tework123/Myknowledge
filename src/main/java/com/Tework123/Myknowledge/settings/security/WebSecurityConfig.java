package com.Tework123.Myknowledge.settings.security;

import com.Tework123.Myknowledge.settings.security.jwt.JwtAuthenticationEntryPoint;
import com.Tework123.Myknowledge.settings.security.jwt.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    final private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    final private UserDetailsService jwtUserDetailsService;
    final private JwtRequestFilter jwtRequestFilter;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.GET,
                                "/error/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/admin/**",
                                "/users",
                                "/book",
                                "/book/{id}",
                                "/relationship"
                        ).authenticated()
                        .requestMatchers(HttpMethod.POST,
                                "/book",
                                "/relationship/{id}"
                        ).authenticated()
                        .requestMatchers(HttpMethod.PATCH,
                                "/book/{id}"
                        ).authenticated()
                        .requestMatchers(HttpMethod.DELETE,
                                "/book/{id}"
                        ).authenticated()
//                                .requestMatchers(
//                                        "/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,
                                "/signin",
                                "/signup"
                        ).permitAll().anyRequest().permitAll());


        http.exceptionHandling((exception) -> exception
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedPage("/error/forbidden"));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

}
