package com.cn.cnEvent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * InvalidInputException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException
{
    public InvalidInputException(String message)
    {
        super(message);
    }
}
