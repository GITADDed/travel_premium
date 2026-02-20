package org.javaguru.travel.insurance.core;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ErrorCodeServiceImpl implements ErrorCodeService {

    private final Environment env;

    @Override
    public String getMessage(String code) {
        String msg = env.getProperty(code);

        if (msg == null) {
            throw new IllegalArgumentException(
                    "No message configured for errorCode=" + code
            );
        }
        return msg;
    }
}
