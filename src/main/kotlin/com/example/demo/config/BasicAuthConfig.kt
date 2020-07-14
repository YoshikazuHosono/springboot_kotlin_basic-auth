package com.example.demo.config

import com.example.demo.provider.BasicAuthProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class BasicAuthConfig : WebSecurityConfigurerAdapter() {

    @Value("\${basicauth.realmName}")
    private lateinit var basicAuthRealmName: String

    @Autowired
    private lateinit var basicAuthProvider: BasicAuthProvider

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()

        http.antMatcher("/simple/**")
        http.authorizeRequests().anyRequest().authenticated()

        http.httpBasic().realmName(basicAuthRealmName)

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(basicAuthProvider)
    }

}