package com.rc.simpleserver.engine.endpoint.factory;

import com.rc.simpleserver.engine.endpoint.DefaultDispatcher;
import com.rc.simpleserver.engine.endpoint.HelloDispatcher;
import com.rc.simpleserver.engine.endpoint.IEndPointDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispatcherFactory {
    @Autowired
    HelloDispatcher helloDispatcher;

    @Autowired
    DefaultDispatcher defaultDispatcher;

    public IEndPointDispatcher getEndPointDispatcher(String endpointName) {
        IEndPointDispatcher dispatcher;
        switch (endpointName) {
            case "hello":
                dispatcher = helloDispatcher;
                break;
            default:
                dispatcher = defaultDispatcher;
                break;
        }

        return dispatcher;
    }
}
