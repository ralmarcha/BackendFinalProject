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
             //   .requestMatchers(HttpMethod.GET, "/post/{id}").hasAnyRole("ADMIN", "USER", "CONTRIBUTOR")
              //  .requestMatchers(HttpMethod.GET, "/author/{id}").hasAnyRole("ADMIN", "USER", "CONTRIBUTOR")
              //  .requestMatchers(HttpMethod.POST, "/post-add").hasAnyRole("ADMIN", "CONTRIBUTOR")
              //  .requestMatchers(HttpMethod.POST, "/author-add").hasRole("ADMIN")
              //  .requestMatchers(HttpMethod.PUT, "post/{id}").hasAnyRole("ADMIN", "CONTRIBUTOR")
              //  .requestMatchers(HttpMethod.PUT, "author/{id}").hasAnyRole("ADMIN", "CONTRIBUTOR")
              //  .requestMatchers(HttpMethod.DELETE, "/post/{id}").hasRole("ADMIN")
              //  .requestMatchers(HttpMethod.DELETE, "/author/{id}").hasRole("ADMIN")
                .anyRequest().permitAll();

        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }

}
