package com.rc.simpleserver.engine.endpoint

abstract class DispatcherFactory {
    abstract fun getEndpointDispatcher() : EndPointDispatcher

    companion object {
         fun getEndPointDispatcher(endpointName: String?): EndPointDispatcher? {
            val dispatcher: EndPointDispatcher = when (endpointName) {
                "hello" -> HelloWorldDispatcher()
                else -> DefaultDispatcher()
            }
            return dispatcher;
        }
    }
}
