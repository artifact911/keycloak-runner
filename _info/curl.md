### Keycloak

```bash
curl -X POST \                                                          
'http://localhost:8080/realms/company-realm/protocol/openid-connect/token' \
-H 'Content-Type: application/x-www-form-urlencoded' \
-d 'client_id=admins-api' \
-d 'grant_type=password' \
-d 'username=alice' \
-d 'password=Alice123!Test'
```


### admins-api

```bash 
curl -i http://localhost:8081/hello/public
```
```bash 
curl -i http://localhost:8081/hello/user
```
```bash 
curl -i http://localhost:8081/hello/admin \
  -H "Authorization: Bearer "
```