package org.javaguru.travel.insurance.rest;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class TestCaseProvider {
    private static final Pattern REQUEST_PATTERN = Pattern.compile("request(\\d+)\\.json");

    private TestCaseProvider() {

    }

    public static Stream<Integer> caseNumbers() {
        try {
            var resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath*:rest/requests/request*.json");

            return Arrays.stream(resources)
                    .map(TestCaseProvider::extractCaseNumber)
                    .sorted();
        } catch (Exception e) {
            throw new RuntimeException("Failed to scan request*.json in classpath:/rest/requests", e);
        }
    }

    private static Integer extractCaseNumber(Resource resource) {
        String fileName = resource.getFilename();

        if (fileName == null) {
            throw new IllegalArgumentException("Resource has no filename: " + resource);
        }

        Matcher m = REQUEST_PATTERN.matcher(fileName);

        if (!m.matches()) {
            throw new IllegalArgumentException("Unexpected request filename: " + fileName);
        }

        return Integer.parseInt(m.group(1));
    }
}
