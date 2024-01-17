package com.findting.exception.notFound;

public class FileNotFoundException extends NotFondException{
    private final static String MESSAGE = "파일이 존재하지 않습니다.";
    public FileNotFoundException() {
        super(MESSAGE);
    }
}
