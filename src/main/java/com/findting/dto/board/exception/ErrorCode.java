package com.findting.dto.board.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ErrorCode {
    private final String code;
    private final String message;

    private Map<String, String> validation = new HashMap<>();

    public void addValidation(String filed, String validation) {
        this.validation.put(filed, validation);
    }
}
