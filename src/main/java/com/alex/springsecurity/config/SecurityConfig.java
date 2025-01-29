package com.alex.springsecurity.config;

import com.alex.springsecurity.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

//    @Bean
//    public UserDetailsService usersdetails() {
//        List<UserDetails> users = List.of(
//                User
//                        .withUsername("alex")
//                        .password(passwordEncoder().encode("alexPrueba"))
//                        .roles("USERS")
//                        .build(),
//                User
//                        .withUsername("user2")
//                        .password(passwordEncoder().encode("user2"))
//                        .roles("OPERATOR")
//                        .build(),
//                User
//                        .withUsername("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .roles("USERS", "ADMIN")
//                        .build());
//        return new InMemoryUserDetailsManager(users);
//    }

    @Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/registro").permitAll()
                        .requestMatchers("/perfiles/**").hasRole("ADMON")
                        .requestMatchers("/cliente/**").hasRole("CLIENTE")
                        .requestMatchers("/reservas/**").hasRole("CLIENTE")
                        .anyRequest().authenticated()
                )
                .formLogin(login ->
                        login.loginPage("/login")
                                .defaultSuccessUrl("/")
                                .permitAll()
                )
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .permitAll()
                );

        return http.build();
    }
}