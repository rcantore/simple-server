package com.rc.simpleserver.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EchoController {

    @GetMapping(value = ["/echo"], consumes = [ MediaType.APPLICATION_JSON_VALUE ])
    @ResponseBody fun doEcho(@RequestBody request: String): String {
        return request
    }
}
