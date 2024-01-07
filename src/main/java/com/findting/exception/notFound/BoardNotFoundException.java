package com.findting.exception.notFound;

public class BoardNotFoundException extends NotFondException {
    private final static String MESSAGE = "해당 글은 찾을 수가 없습니다.";

    public BoardNotFoundException() {
        super(MESSAGE);
    }
}
