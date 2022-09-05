package com.rc.simpleserver.engine.endpoint

class DefaultDispatcher : EndPointDispatcher {
    override fun dispatch(): String {
        return """{"msg": "default implementation"}"""
    }

    override fun dispatchGet(): String {
        return dispatch()
    }

    override fun dispatchPost(): String {
        return dispatch()
    }
}
