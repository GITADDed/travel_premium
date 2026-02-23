package org.javaguru.travel.insurance.core.utils;

public interface ErrorCodeUtil {
    String getMessage(String code);
    String getMessageWithUpdate(String code, String message);
}
