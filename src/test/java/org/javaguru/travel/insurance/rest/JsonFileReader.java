package org.javaguru.travel.insurance.rest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonFileReader {

    public String readJsonFromFile(String filePath) throws IOException {
        String result;
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (is == null) {
                throw new IOException();
            }
            result = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
        return result;
    }
}
