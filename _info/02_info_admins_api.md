## admins-api работает с realm_access

### SecurityConfig сервиса

Что здесь важно:
- STATELESS — сервер не хранит HTTP-сессию пользователя.
- oauth2ResourceServer { it.jwt {} } — Spring Security использует JWT-аутентификацию.
- permitAll() — разрешает запрос без токена.
- authenticated() — требует действительный токен.
- hasRole("ADMIN") — требует полномочие ROLE_ADMIN.

Последний пункт заслуживает внимания: Keycloak выдаёт роль ADMIN, а Spring Security при 
hasRole("ADMIN") ищет полномочие ROLE_ADMIN. 
По умолчанию Spring Security не обязан автоматически превращать realm_access.roles 
в такие полномочия. Это мы настроим отдельно на следующем шаге.

Имеется в виду, что сейчас приложение не умеет прочесть роли из realm_access.roles. 
На этом этапе обучения мы научим приложение это делать

### KeycloakJwtConfig

Что здесь происходит:
1. Читаем claim realm_access.
2. Получаем список ролей из roles.
3. Преобразуем ADMIN в ROLE_ADMIN, а USER — в ROLE_USER.
4. Сохраняем стандартные полномочия из scopes, которые обрабатывает Spring Security.

- Подключи конвертер в SecurityConfig