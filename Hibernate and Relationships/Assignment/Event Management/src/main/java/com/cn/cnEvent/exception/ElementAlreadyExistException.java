package com.cn.cnEvent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ElementAlreadyExistException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ElementAlreadyExistException extends RuntimeException
{
    public ElementAlreadyExistException(String message)
    {
        super(message);
    }
}
