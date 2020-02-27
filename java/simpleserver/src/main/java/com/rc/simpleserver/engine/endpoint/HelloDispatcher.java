package com.rc.simpleserver.engine.endpoint;

import org.springframework.stereotype.Component;

@Component
public class HelloDispatcher implements IEndPointDispatcher {
    @Override
    public String dispatch() {
        return "{\n" +
                "    \"msg\": \"hello world\"\n" +
                "}\n";
    }
}
