package com.findting.advice;

import com.findting.model.exception.ErrorCode;
import com.findting.exception.notFound.NotFondException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerHelper {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorCode validationException(MethodArgumentNotValidException exception) {
        ErrorCode error = new ErrorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()), "잘못된 코드입니다.");

        if (exception.hasErrors()) {
            List<FieldError> fieldErrors = exception.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                error.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }

        return error;
    }

    @ExceptionHandler({NotFondException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorCode validation(NotFondException exception) {
        return new ErrorCode(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getMessage());
    }


}
