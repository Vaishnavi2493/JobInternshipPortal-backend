package com.example.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.security.JwtAuthFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .cors(cors -> {})

            .authorizeHttpRequests(auth -> auth

                // Login & Registration - Public
            		.requestMatchers(
            			    "/api/users/register",
            			    "/api/users/login"
            			).permitAll()


                // =========================
                // JOB MANAGEMENT
                // =========================

                // Admin - Add Job
                .requestMatchers(
                    HttpMethod.POST,
                    "/jobs"
                ).hasRole("ADMIN")

                // Admin - Update Job
                .requestMatchers(
                    HttpMethod.PUT,
                    "/jobs/**"
                ).hasRole("ADMIN")

                // Admin - Delete Job
                .requestMatchers(
                    HttpMethod.DELETE,
                    "/jobs/**"
                ).hasRole("ADMIN")


                // =========================
                // JOB APPLICATION
                // =========================

                // Job Seeker - Apply for Job
                .requestMatchers(
                    HttpMethod.POST,
                    "/applications"
                ).authenticated()

                // Job Seeker - My Applications
                .requestMatchers(
                    HttpMethod.GET,
                    "/applications/user"
                ).authenticated()

                // Admin - View All Applications
                .requestMatchers(
                    HttpMethod.GET,
                    "/applications"
                ).hasRole("ADMIN")

                // Admin - View Applications of a Job
                .requestMatchers(
                    HttpMethod.GET,
                    "/applications/job/**"
                ).hasRole("ADMIN")

                // Admin - Update Application Status
                .requestMatchers(
                    HttpMethod.PUT,
                    "/applications/**"
                ).hasRole("ADMIN")


                // Other requests
                .anyRequest().authenticated()
            );

        http.addFilterBefore(
            jwtAuthFilter,
            UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
            List.of("http://localhost:4200")
        );

        configuration.setAllowedMethods(
            List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );

        configuration.setAllowedHeaders(
            List.of("*")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}