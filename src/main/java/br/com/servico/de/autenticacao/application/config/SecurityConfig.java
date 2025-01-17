package br.com.servico.de.autenticacao.application.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/publico").permitAll() // Permite acesso público ao endpoint
                        .anyRequest().authenticated()           // Protege outros endpoints
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // Página de login personalizada
                );

        // Configura o Resource Server explicitamente
        http
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationManagerResolver(authenticationManagerResolver())
                );

        // Configura o tratamento de exceções
        http
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                );

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Configura o decodificador de JWT para validar os tokens emitidos pelo Auth0
        return JwtDecoders.fromIssuerLocation("https://SEU_AUTH0_DOMAIN/");
    }

    @Bean
    public AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver() {
        return request -> {
            JwtAuthenticationProvider provider = new JwtAuthenticationProvider(jwtDecoder());
            return authentication -> provider.authenticate((JwtAuthenticationToken) authentication);
        };
    }
}
