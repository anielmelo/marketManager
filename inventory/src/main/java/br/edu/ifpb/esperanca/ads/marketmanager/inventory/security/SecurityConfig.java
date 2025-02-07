package br.edu.ifpb.esperanca.ads.marketmanager.inventory.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                return http
                                .csrf((AbstractHttpConfigurer::disable))
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(HttpMethod.GET, "/inventory/**").hasAuthority("STOCKKEEPER")
                                                .requestMatchers(HttpMethod.POST, "/inventory/**").hasAuthority("STOCKKEEPER")
                                                .requestMatchers(HttpMethod.PUT, "/inventory/**").hasAuthority("STOCKKEEPER")
                                                .requestMatchers(HttpMethod.DELETE, "/inventory/**").hasAuthority("STOCKKEEPER")
                                                .requestMatchers(HttpMethod.OPTIONS, "/inventory/**").hasAuthority("STOCKKEEPER")
                                                .anyRequest().authenticated())
                                .oauth2ResourceServer(oauth2 -> oauth2
                                                .jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter())))
                                .build();
        }
}
