package com.security.bank.exception;

/**
 * CardNotFoundException
 */
public class CardNotFoundException extends RuntimeException
{
    public CardNotFoundException(String message)
    {
        super(message);
    }
}