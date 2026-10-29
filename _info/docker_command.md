### Поднимаем env

docker run --name keycloak \
-p 127.0.0.1:8080:8080 \
-e KC_BOOTSTRAP_ADMIN_USERNAME=admin \
-e KC_BOOTSTRAP_ADMIN_PASSWORD=admin \
quay.io/keycloak/keycloak:26.8.1 \
start-dev

- --name keycloak — имя контейнера.
- -p 127.0.0.1:8080:8080 — открываем сервер на локальном компьютере.
- KC_BOOTSTRAP_ADMIN_USERNAME и KC_BOOTSTRAP_ADMIN_PASSWORD — создаём начальную учётную запись администратора.
- start-dev — запускаем сервер в режиме разработки.