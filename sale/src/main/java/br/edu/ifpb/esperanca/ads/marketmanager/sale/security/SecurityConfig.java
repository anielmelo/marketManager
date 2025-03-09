package br.edu.ifpb.esperanca.ads.marketmanager.sale.security;

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

                // hasAuthority("SALE_KEEPER")
                return http
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(HttpMethod.GET, "/sale/**").hasRole("SALE_KEEPER")
                                                .requestMatchers(HttpMethod.POST, "/sale/**").hasRole("SALE_KEEPER")
                                                .requestMatchers(HttpMethod.PUT, "/sale/**").hasRole("SALE_KEEPER")
                                                .requestMatchers(HttpMethod.DELETE, "/sale/**").hasRole("SALE_KEEPER")
                                                .requestMatchers(HttpMethod.OPTIONS, "/sale/**").permitAll()
                                                .anyRequest().authenticated())
                                .oauth2ResourceServer(oauth2 -> oauth2
                                                .jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter())))
                                .build();
        }
}
