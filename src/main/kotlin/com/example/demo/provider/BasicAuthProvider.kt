package com.example.demo.provider

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class BasicAuthProvider : AuthenticationProvider {

    @Value("\${basicauth.name}")
    private lateinit var basicAuthName: String

    @Value("\${basicauth.pass}")
    private lateinit var basicAuthPass: String

    override fun authenticate(authentication: Authentication): Authentication {
        val inputName = authentication.name
        val inputPass = authentication.credentials.toString()

        if (inputName == basicAuthName && inputPass == basicAuthPass) {
            return UsernamePasswordAuthenticationToken(inputName, inputPass, authentication.authorities)
        } else {
            throw  BadCredentialsException("not invalid! get away!")
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}