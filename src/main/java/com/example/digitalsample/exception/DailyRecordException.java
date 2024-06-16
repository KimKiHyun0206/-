package com.example.digitalsample.exception;

public class DailyRecordException extends BusinessException{
    public DailyRecordException() {
        super(ErrorMessage.USER_NOT_FOUND_ERROR);
    }
}