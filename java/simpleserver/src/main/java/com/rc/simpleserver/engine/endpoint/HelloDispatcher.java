package com.rc.simpleserver.engine.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
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
        URL url = this.getClass().getClassLoader().getResource("templates/endpoint/hello/get");
        File file;
        try {
            assert url != null;
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
