package com.findting.dto.board.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorCode {
    private final String code;
    private final String message;

    public ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // validation 체크
    private final Map<String, String> validation = new HashMap<>();

    public void addValidation(String filed, String validation) {
        this.validation.put(filed, validation);
    }
}
