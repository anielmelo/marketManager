package br.edu.ifpb.esperanca.ads.marketmanager.inventory.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                // hasAuthority("STOCK_KEEPER")
                return http
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(HttpMethod.GET, "/inventory/**").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/inventory/**").hasRole("STOCK_KEEPER")
                                                .requestMatchers(HttpMethod.PUT, "/inventory/**").hasRole("STOCK_KEEPER")
                                                .requestMatchers(HttpMethod.DELETE, "/inventory/**").hasRole("STOCK_KEEPER")
                                                .requestMatchers(HttpMethod.OPTIONS, "/inventory/**").permitAll()
                                                .anyRequest().authenticated())
                                .oauth2ResourceServer(oauth2 -> oauth2
                                                .jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter())))
                                .build();
        }
}
