package com.example.filterchain3.configuration;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager()
    {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("user")
                .build();
        UserDetails admin= User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("admin")
                .build();
          return  new InMemoryUserDetailsManager(user,admin);
    }
    @Bean
   public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception
   {   log.info("inside security filter chain");
       http                                  .csrf(AbstractHttpConfigurer::disable);
       http                                  .authorizeHttpRequests((req)->req
                                             .requestMatchers("/admin").hasRole("admin")
                                              .requestMatchers("/user").hasRole("user")
                                              .requestMatchers("/hello").permitAll()
                                             .anyRequest().authenticated());
       http                                    .httpBasic(Customizer.withDefaults());
       return http.build();
   }
}
