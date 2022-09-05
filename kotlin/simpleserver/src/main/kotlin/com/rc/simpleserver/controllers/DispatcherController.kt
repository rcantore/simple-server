package com.rc.simpleserver.controllers

import com.rc.simpleserver.engine.endpoint.DispatcherFactory
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*

@RestController
class DispatcherController {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/{endPoint}*/**")
    fun dispatchGet(): String? {
        val dispatcher = DispatcherFactory.getEndPointDispatcher("hello")
        return dispatcher?.dispatchGet();
    }

    @PostMapping("/{endPoint}*/**")
    fun dispatchPost(): String? {
        val dispatcher = DispatcherFactory.getEndPointDispatcher("hello")
        return dispatcher?.dispatchPost();
    }

}
