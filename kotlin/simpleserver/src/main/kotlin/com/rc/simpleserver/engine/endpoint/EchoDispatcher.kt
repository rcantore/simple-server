package com.rc.simpleserver.engine.endpoint

import mu.KotlinLogging

class EchoDispatcher : EndPointDispatcher {
    private val logger = KotlinLogging.logger {}

    override fun dispatch(): String? {
        TODO("Not yet implemented")
    }

    override fun dispatchGet(): String? {
        return dispatch()
    }

    override fun dispatchPost(): String? {
        return dispatch()
    }
}
