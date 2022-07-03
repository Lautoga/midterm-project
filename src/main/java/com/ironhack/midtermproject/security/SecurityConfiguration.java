package com.ironhack.midtermproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/checking/{id}").hasAnyRole("ACCOUNT_HOLDER","ADMIN")
                .antMatchers(HttpMethod.POST, "/checking").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/checking/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/credit-card/{id}").hasAnyRole("ACCOUNT_HOLDER","ADMIN")
                .antMatchers(HttpMethod.POST, "/credit-card").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/credit-card/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/savings/{id}").hasAnyRole("ACCOUNT_HOLDER","ADMIN")
                .antMatchers(HttpMethod.POST, "/savings").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/savings/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/student-checking/{id}").hasAnyRole("ACCOUNT_HOLDER","ADMIN")
                .antMatchers(HttpMethod.PATCH,"/account/{id}/balance").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH,"/account/{id}/transfer").hasRole("ACCOUNT-HOLDER")
                .antMatchers(HttpMethod.POST, "/third-party").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/third-party/{id}").hasRole("ADMIN")
                .anyRequest().permitAll();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
