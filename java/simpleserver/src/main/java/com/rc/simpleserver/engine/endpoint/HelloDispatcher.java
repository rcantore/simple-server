package com.rc.simpleserver.engine.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = resolver.getResources("classpath:templates/endpoint/hello/get");

            for (Resource resource : resources) {
                InputStream inputStream = resource.getInputStream();

                // Do something with the input stream
                logger.info("is " + resource.getFilename());
                logger.info("is " + resource.getURI().toString());
                logger.info("is " + resource.getURL().toString());
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(isr);
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                isr.close();
                inputStream.close();
                logger.info("sb " + sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "{}";
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
