package com.rc.simpleserver.controllers;

import com.rc.simpleserver.engine.endpoint.IEndPointDispatcher;
import com.rc.simpleserver.engine.endpoint.factory.DispatcherFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class DispatcherController {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherController.class);

    @Autowired
    DispatcherFactory dispatcherFactory;

    @RequestMapping(value="/{endPoint}*/**", method= RequestMethod.GET)
    public @ResponseBody String dispatch(@PathVariable String endPoint) {
        logger.info("endpoint " + endPoint);
        IEndPointDispatcher endPointDispatcher = dispatcherFactory.getEndPointDispatcher(endPoint);

        return endPointDispatcher.dispatchGet();
    }

    @RequestMapping(value = "/{endPoint}*/**",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, "application/vnd.lockstate+json", "application/x-www-form-urlencoded"},
            produces = {MediaType.APPLICATION_JSON_VALUE, "application/vnd.lockstate+json", "application/x-www-form-urlencoded"})
    public @ResponseBody String dispatchPost(@PathVariable String endPoint) {
        logger.info("endpoint " + endPoint);
        IEndPointDispatcher endPointDispatcher = dispatcherFactory.getEndPointDispatcher(endPoint);

        return endPointDispatcher.dispatchPost();
    }

}
