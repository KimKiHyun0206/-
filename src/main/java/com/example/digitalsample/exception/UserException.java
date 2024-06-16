
package com.example.digitalsample.exception;

public class UserException extends BusinessException{
    public UserException() {
        super(ErrorMessage.USER_NOT_FOUND_ERROR);
    }
}