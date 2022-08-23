package com.rc.simpleserver.controllers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.rc.simpleserver.config.EndpointsConfig;
import com.rc.simpleserver.engine.endpoint.IEndPointDispatcher;
import com.rc.simpleserver.engine.endpoint.factory.DispatcherFactory;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class DispatcherController {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherController.class);

    private final EndpointsConfig endpointsConfig;

    private final DispatcherFactory dispatcherFactory;

    @RequestMapping(value="/{endPoint}*/**", method= RequestMethod.GET)
    public @ResponseBody String dispatch(@PathVariable String endPoint) {
        logger.info("endpoint " + endPoint);

        logger.info("enpointsConfiug " + endpointsConfig.getEndpoint());
        logger.info("enpointsConfiug " + endpointsConfig.getEndpoint().size());

        IEndPointDispatcher endPointDispatcher = dispatcherFactory.getEndPointDispatcher(endPoint);

        return endPointDispatcher.dispatchGet();
    }

    @RequestMapping(value = "/{endPoint}*/**",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, "application/vnd.lockstate+json", "application/x-www-form-urlencoded", MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, "application/vnd.lockstate+json", "application/x-www-form-urlencoded", MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody String dispatchPost(@PathVariable String endPoint) {
        logger.info("endpoint " + endPoint);

        logger.info("enpointsConfiug " + endpointsConfig.getEndpoint());
        logger.info("enpointsConfiug " + endpointsConfig.getEndpoint().size());

        IEndPointDispatcher endPointDispatcher = dispatcherFactory.getEndPointDispatcher(endPoint);

        return endPointDispatcher.dispatchPost();
    }


    @RequestMapping(value = "/{endPoint}*/**",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, "application/vnd.lockstate+json", "application/x-www-form-urlencoded", MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, "application/vnd.lockstate+json", "application/x-www-form-urlencoded", MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody String dispatchPut(@PathVariable String endPoint) {
        IEndPointDispatcher endPointDispatcher = dispatcherFactory.getEndPointDispatcher(endPoint);

        return endPointDispatcher.dispatchPost();
    }

}
