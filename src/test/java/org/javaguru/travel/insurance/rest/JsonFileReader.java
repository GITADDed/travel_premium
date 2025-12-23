package org.javaguru.travel.insurance.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public class JsonFileReader {

    public String readJsonFromFile(String filePath) {
        String result;
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + filePath);
            }
            result = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            return result;
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read resource: " + filePath, e);
        }
    }
}
