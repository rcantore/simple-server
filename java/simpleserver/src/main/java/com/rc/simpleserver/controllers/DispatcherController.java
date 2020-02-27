package com.rc.simpleserver.controllers;

import com.rc.simpleserver.engine.endpoint.IEndPointDispatcher;
import com.rc.simpleserver.engine.endpoint.factory.DispatcherFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DispatcherController {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherController.class);

    @Autowired
    DispatcherFactory dispatcherFactory;

    @RequestMapping(value="/{endPoint}*/**", method= RequestMethod.GET)
    public @ResponseBody String dispatch(@PathVariable String endPoint) {
        logger.info("endpoint " + endPoint);
        IEndPointDispatcher endPointDispatcher = dispatcherFactory.getEndPointDispatcher(endPoint);

        return endPointDispatcher.dispatch();
    }

}
