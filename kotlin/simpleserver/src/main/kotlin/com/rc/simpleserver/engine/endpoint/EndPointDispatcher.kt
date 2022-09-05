package com.rc.simpleserver.engine.endpoint

interface EndPointDispatcher {
    fun dispatch(): String?

    fun dispatchGet(): String?

    fun dispatchPost(): String?
}
