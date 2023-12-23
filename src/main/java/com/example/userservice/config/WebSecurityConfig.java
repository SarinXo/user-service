package com.example.userservice.config;

import com.example.userservice.handlers.AuthSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/users/**").hasRole("USER")
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login-page")
                        .permitAll()
                        .successHandler(new AuthSuccessHandler())
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                );

        return http.build();
    }

    @Bean
    public UserDetailsService adminDetailsService() {
        UserDetails admin =
                User.builder()
                        .username("a")
                        .password("{bcrypt}$2a$12$0pSesoQ5L8aKCmSvmd8GLOazeV17nAsdBNSHPdTZKtDtesAmTq9gW")//pass
                        .roles("ADMIN")
                        .build();
        UserDetails user =
                User.builder()
                        .username("u")
                        .password("{bcrypt}$2a$12$0pSesoQ5L8aKCmSvmd8GLOazeV17nAsdBNSHPdTZKtDtesAmTq9gW")//pass
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
