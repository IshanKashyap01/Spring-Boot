package com.codingninjas.EVotingSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class VoteAlreadyCastedException extends RuntimeException
{
    public VoteAlreadyCastedException(String message)
    {
        super(message);
    }
}
