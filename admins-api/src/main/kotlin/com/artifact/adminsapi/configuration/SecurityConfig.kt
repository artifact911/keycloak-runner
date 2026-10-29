package com.artifact.adminsapi.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity
class SecurityConfig(
    private val jwtAuthenticationConverter: JwtAuthenticationConverter
) {

    /**
     * Что здесь важно:
     * - STATELESS — сервер не хранит HTTP-сессию пользователя.
     * - oauth2ResourceServer { it.jwt {} } — Spring Security использует JWT-аутентификацию.
     * - permitAll() — разрешает запрос без токена.
     * - authenticated() — требует действительный токен.
     * - hasRole("ADMIN") — требует полномочие ROLE_ADMIN.
     */

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/hello/public").permitAll()
                    .requestMatchers("/hello/admin").hasRole("ADMIN")
                    .requestMatchers("/hello/user").authenticated()
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer {
                it.jwt {jwt ->
                    jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)
                }
            }
            .build()
}