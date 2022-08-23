package com.rc.simpleserver.engine.endpoint;

public interface IEndPointDispatcher {
    String dispatch();

    String dispatchGet();

    String dispatchPost();
}
