package com.artifact.keycloakrunner

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KeycloakRunnerApplication

fun main(args: Array<String>) {
    runApplication<KeycloakRunnerApplication>(*args)
}
