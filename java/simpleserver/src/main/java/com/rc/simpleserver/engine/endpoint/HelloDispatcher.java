package com.rc.simpleserver.engine.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

        List<String> resourceFiles = null;
        try {
            resourceFiles = getResourceFiles(path);
            resourceFiles.forEach(s -> logger.info("arhicve " + s));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "{}";
    }
    private List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try (
                InputStream in = getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }

        return filenames;
    }

    private InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
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
