package com.example.demo.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/simple")
class SimpleRestController {

    @GetMapping("/get")
    fun get(): String {
        return "hello!"
    }

    @GetMapping("/get2")
    fun get2(@RequestParam name: String): String {
        return "hello ${name}!"
    }

    @GetMapping("/get3")
    fun get3(@ModelAttribute request: Get3Request): String {
        return "hello ${request.name}!"
    }

    @PostMapping("/post")
    fun post(): String {
        return "hello!"
    }

    @PostMapping("/post2")
    fun post(@RequestBody request: Post2Request): String {
        return "hello ${request.name}!"
    }

}

data class Get3Request(
        val name: String
)

data class Post2Request(
        val name: String
)