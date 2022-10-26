package com.asterlsker.housepit.api.interfaces

import com.asterlsker.housepit.api.common.AUTH_SIGNIN
import com.asterlsker.housepit.core.annotation.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {
    @Authentication()
    @GetMapping(AUTH_SIGNIN)
    fun signIn() {
        println("A")
    }
}