package com.rc.simpleserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class SimpleserverApplication

fun main(args: Array<String>) {
    runApplication<SimpleserverApplication>(*args)
}

