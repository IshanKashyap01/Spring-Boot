package com.example.MovieTicket.MovieBooking.Exceptions;

public class InputValidationException extends RuntimeException
{
    public InputValidationException(String message)
    {
        super(message);
    }
}
