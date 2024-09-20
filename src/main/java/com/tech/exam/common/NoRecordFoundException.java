package com.tech.exam.common;

import lombok.Getter;

@Getter
public class NoRecordFoundException extends RuntimeException {
    private final String message;

    public NoRecordFoundException(String message) {
        super();
        this.message = message;
    }
}
