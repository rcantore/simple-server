package com.rc.simpleserver.engine.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class DevicesDispatcher implements IEndPointDispatcher {
    private static final Logger logger = LoggerFactory.getLogger(DevicesDispatcher.class);

    @Override
    public String dispatch() {
        return null;
    }

    @Override
    public String dispatchPost() {
        return null;
    }

    @Override
    public String dispatchGet() {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        StringBuilder mockResponse = new StringBuilder("{}");
        try {
            Resource[] resources = resolver.getResources("classpath:templates/endpoint/devices.get/*.*");
            if (resources.length > 0) {
                Resource resource = resources[0];

                InputStream inputStream = resources[0].getInputStream();
                // Do something with the input stream

                logger.info("resource " + resource);
                logger.info("resource " + resource.getFilename());

                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                    mockResponse = new StringBuilder();
                    bufferedReader.lines().forEach(mockResponse::append);

                    logger.debug("mock response: " + mockResponse.toString());
                }
            } else {
                logger.error("no POST response configured");

            }

        } catch (IOException e) {
            logger.error("no POST response configured", e);
        }

        return mockResponse.toString();
    }
}
