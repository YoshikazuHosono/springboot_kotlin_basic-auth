package com.example.demo.provider

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class BasicAuthProvider : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val inputName = authentication.name
        val inputPass = authentication.credentials.toString()

        val expectName = "hosono"
        val expectPass = "lovelove"

        if (inputName == expectName && inputPass == expectPass) {
            return UsernamePasswordAuthenticationToken(inputName, inputPass, authentication.authorities)
        } else {
            throw  BadCredentialsException("not invalid! get away!")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}