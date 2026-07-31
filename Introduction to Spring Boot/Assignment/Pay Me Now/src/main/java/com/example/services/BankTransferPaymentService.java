package com.example.services;

public class BankTransferPaymentService implements PaymentService
{
    @Override
    public void processPayment(double amount)
    {
        System.out.println("Processing bank transfer of $" + amount);
    }
}
