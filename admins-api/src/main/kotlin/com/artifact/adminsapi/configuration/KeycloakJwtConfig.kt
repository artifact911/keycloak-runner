package com.artifact.adminsapi.configuration

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KeycloakJwtConfig {

    /**
     * Что здесь происходит:
     * 1. Читаем claim realm_access.
     * 2. Получаем список ролей из roles.
     * 3. Преобразуем ADMIN в ROLE_ADMIN, а USER — в ROLE_USER.
     * 4. Сохраняем стандартные полномочия из scopes, которые обрабатывает Spring Security.
     */
    @Bean
    fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val scopesConverter = JwtGrantedAuthoritiesConverter()

        val rolesConverter = Converter<Jwt, Collection<GrantedAuthority>> { jwt ->
            val roles = jwt.getClaimAsMap("realm_access")
                ?.get("roles") as? Collection<*>
                ?: emptyList<Any>()

            roles.filterIsInstance<String>()
                .map { SimpleGrantedAuthority("ROLE_$it") }
        }

        val converter = JwtAuthenticationConverter()

        converter.setJwtGrantedAuthoritiesConverter { jwt ->
            (scopesConverter.convert(jwt).orEmpty() + rolesConverter.convert(jwt))
                .distinct()
        }

        return converter
    }
}