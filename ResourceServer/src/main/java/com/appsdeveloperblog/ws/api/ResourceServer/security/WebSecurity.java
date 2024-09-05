package com.appsdeveloperblog.ws.api.ResourceServer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

//This class is used to configure http security
@EnableMethodSecurity(securedEnabled = true) //This Enables the method level security
@Configuration
@EnableWebSecurity
public class WebSecurity{
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        http.authorizeHttpRequests(authz ->
                                authz
                                        .requestMatchers(HttpMethod.GET, "/users/status/check")
//                                .hasAnyAuthority("SCOPE_profile")
//                                .hasRole("developer")
//                                .hasAnyRole("developer", "user")
                                        .hasAuthority("SCOPE_profile")
//                                        .requestMatchers(HttpMethod.GET, "/users/{id}")
//                                        .hasRole("ROLE_developer")
                                        .anyRequest().authenticated()
                )
                //below line is for scope access hard coded and not from the jwt
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}));
//                below line of the code is used to get the scope from the access token
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        return http.build();
    }
}
