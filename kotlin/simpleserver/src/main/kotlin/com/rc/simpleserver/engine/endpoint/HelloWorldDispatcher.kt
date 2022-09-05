package com.rc.simpleserver.engine.endpoint

import mu.KotlinLogging
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import java.io.File

class HelloWorldDispatcher : EndPointDispatcher {
    private val logger = KotlinLogging.logger {}

    override fun dispatch(): String? {
        val path = "templates/endpoint/hello/get"
        val resolver = PathMatchingResourcePatternResolver()

        val resources = resolver.getResources("classpath:templates/endpoint/hello/get/*.*");
        resources.forEach {
            logger.info("resource $it");
            logger.info("resource ${it.filename}");
            logger.info( "resource full path ${it.url}")
        }

        return resources[0].url.let { File(it.path).readText() }
    }

    override fun dispatchGet(): String? {
        return dispatch()
    }

    override fun dispatchPost(): String? {
        return dispatch()
    }

    companion object Factory : DispatcherFactory() {
        override fun getEndpointDispatcher() = HelloWorldDispatcher()
    }
}
