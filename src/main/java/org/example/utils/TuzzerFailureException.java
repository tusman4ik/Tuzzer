package org.example.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TuzzerFailureException extends RuntimeException {
    public TuzzerFailureException() {
        super();
    }

    @SuppressWarnings("unused")
    public TuzzerFailureException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public TuzzerFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    @SuppressWarnings("unused")
    public TuzzerFailureException(Throwable cause) {
        super(cause);
    }

    @SuppressWarnings("unused")
    protected TuzzerFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
