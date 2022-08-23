package com.rc.simpleserver.engine.endpoint;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Component
public class HelloDispatcher implements IEndPointDispatcher {
    private static final Logger logger = LoggerFactory.getLogger(HelloDispatcher.class);

    @Override
    public String dispatch() {
        //deafults to GET
        return dispatchGet();
    }

    @Override
    public String dispatchGet() {
        String path = "templates/endpoint/hello/get";
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        StringBuilder mockResponse = new StringBuilder("{}");
        try {
            Resource[] resources = resolver.getResources("classpath:templates/endpoint/hello/get/*.*");
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
                logger.error("no GET response configured");

            }

        } catch (IOException e) {
            logger.error("no GET response configured", e);
        }

        return mockResponse.toString();
    }

    @Override
    public String dispatchPost() {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        StringBuilder mockResponse = new StringBuilder("{}");
        try {
            Resource[] resources = resolver.getResources("classpath:templates/endpoint/hello/post/*.*");
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

    @Deprecated
    public String dispatchGetOriginal() {
        URL url = this.getClass().getClassLoader().getResource("templates/endpoint/hello/get");
        File file;
        try {
            assert url != null;
            logger.debug("url " + url);
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }

        String response = "";

        if (file.exists() && file.isDirectory() && Objects.requireNonNull(file.listFiles()).length > 0) {
            logger.debug("path exists!");
            File[] files = file.listFiles();
            assert files != null;
            File mockResponseFile = files[0];

            StringBuilder contentBuilder = new StringBuilder();
            try (Stream<String> stream = Files.lines(mockResponseFile.toPath(), StandardCharsets.UTF_8)) {
                stream.forEach(s -> contentBuilder.append(s).append("\n"));
            } catch (IOException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }

            logger.debug("Content:");
            logger.debug(contentBuilder.toString());

            response = contentBuilder.toString();
        } else {
            logger.error("no GET response configured");
        }

        return response;
    }
}
