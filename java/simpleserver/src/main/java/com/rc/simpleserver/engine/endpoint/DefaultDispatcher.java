package com.rc.simpleserver.engine.endpoint;

import org.springframework.stereotype.Component;

@Component
public class DefaultDispatcher implements IEndPointDispatcher {
    @Override
    public String dispatch() {
        return "{\n" +
                "    \"msg\": \"default implementation\"\n" +
                "}\n";
    }

    @Override
    public String dispatchGet() {
        return dispatch();
    }
}
