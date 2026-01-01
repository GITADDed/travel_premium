package org.javaguru.travel.insurance.rest.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private static final long SLOW_THRESHOLD_MS = 300;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        long start = System.nanoTime();

        // Корреляционный id (чтобы связать все логи одного запроса)
        String requestId = request.getHeader("X-Request-Id");
        if (requestId == null || requestId.isBlank()) {
            requestId = UUID.randomUUID().toString();
        }

        MDC.put("requestId", requestId);

        try {
            filterChain.doFilter(request, response);
        } finally {
            long ms = (System.nanoTime() - start) / 1_000_000;

            String method = request.getMethod();
            String uri = request.getRequestURI();
            int status = response.getStatus();

            if (ms > SLOW_THRESHOLD_MS) {
                log.warn("HTTP {} {} -> {} ({} ms, threshold={} ms)", method, uri, status, ms, SLOW_THRESHOLD_MS);
            } else {
                log.info("HTTP {} {} -> {} ({} ms)", method, uri, status, ms);
            }

            MDC.remove("requestId");
        }
    }
}
