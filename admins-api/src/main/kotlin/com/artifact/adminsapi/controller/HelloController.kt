package com.artifact.adminsapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController {

    @GetMapping("/public")
    fun publicEndpoint() = mapOf("message" to "Hello, everyone!")

    @GetMapping("/user")
    fun userEndpoint() = mapOf("message" to "Hello, authenticated user!")

    @GetMapping("/admin")
    fun adminEndpoint() = mapOf("message" to "Hello, admin!")
}