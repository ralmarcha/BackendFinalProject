package com.ironhack.backendProject.security;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;

@Configuration
@EnableGlobalAuthentication
public class SecurityConfiguration {

    @Bean
    PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
        return authConf.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        httpSecurity.authorizeHttpRequests()
               .requestMatchers(HttpMethod.GET, "/check-user-balance").hasAnyRole("ADMIN", "ACCOUNT_HOLDER" )
               .requestMatchers(HttpMethod.POST, "/transfer").hasAnyRole("ADMIN", "ACCOUNT_HOLDER")
               .requestMatchers(HttpMethod.GET, "/accounts").hasRole("ADMIN")
               .requestMatchers(HttpMethod.POST, "/create-checking-account").hasRole("ADMIN")
               .requestMatchers(HttpMethod.POST, "/create-savings-account").hasAnyRole("ADMIN")
               .requestMatchers(HttpMethod.POST, "/create-credit-card").hasAnyRole("ADMIN")
               .requestMatchers(HttpMethod.DELETE, "/delete-account/{id}").hasRole("ADMIN")
               .requestMatchers(HttpMethod.GET, "/account/{id}").hasRole("ADMIN")
               .requestMatchers(HttpMethod.GET, "/check-balance/{id}").hasRole("ADMIN")
               .requestMatchers(HttpMethod.PATCH, "/set-balance/{id}").hasRole("ADMIN")
               .requestMatchers(HttpMethod.PUT, "/update-checking-account/{id}").hasRole("ADMIN")
               .requestMatchers(HttpMethod.POST, "/transfer/send").hasAnyRole("THIRD_PARTY", "ADMIN")
               .requestMatchers(HttpMethod.POST, "/transfer/receive").hasAnyRole("THIRD_PARTY", "ADMIN")
               .requestMatchers(HttpMethod.POST, "/create-account-holder").hasRole("ADMIN")
               .requestMatchers(HttpMethod.POST, "/create-third-party").hasRole("ADMIN")
               .requestMatchers(HttpMethod.POST, "/create-admin").hasRole("ADMIN")
               .anyRequest().permitAll();

        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }

}
