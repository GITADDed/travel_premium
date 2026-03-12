package org.javaguru.travel.insurance.core.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ErrorCodeUtilImplTest {

    @Mock
    Environment env;

    @InjectMocks
    ErrorCodeUtilImpl errorCodeUtil;

    @Test
    void shouldReturnMessageByErrorCode() {
        when(env.getProperty(Mockito.anyString())).thenReturn("Error message");

        String msg = errorCodeUtil.getMessage("ERROR_CODE_1000");

        assertEquals("Error message", msg);
        verify(env, Mockito.times(1)).getProperty(Mockito.anyString());
    }

    @Test
    void shouldReturnUpdatedMessageByErrorCodeAndAddictiveMessage() {
        when(env.getProperty(Mockito.anyString())).thenReturn("Error message %s");

        String msg = errorCodeUtil.getMessageWithUpdate("ERROR_CODE_1000", "update");

        assertEquals("Error message update", msg);
        verify(env, Mockito.times(1)).getProperty(Mockito.anyString());
    }

    @Test
    void shouldThrowExceptionWhenErrorCodeNotExist() {
        when(env.getProperty(Mockito.anyString())).thenReturn(null);

        assertThrows(IllegalArgumentException.class,
                () -> errorCodeUtil.getMessage("ERROR_CODE_1000"));
        verify(env, Mockito.times(1)).getProperty(Mockito.anyString());
    }

    @Test
    void shouldThrowExceptionWhenErrorCodeNotExistForUpdateMessageMethod() {
        when(env.getProperty(Mockito.anyString())).thenReturn(null);

        assertThrows(IllegalArgumentException.class,
                () -> errorCodeUtil.getMessageWithUpdate("ERROR_CODE_1000", "update"));
        verify(env, Mockito.times(1)).getProperty(Mockito.anyString());
    }

}