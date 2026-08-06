package com.cn.cnpayment.service;

import com.cn.cnpayment.dal.PaymentDetailsDAL;
import com.cn.cnpayment.entity.PaymentDetails;
import com.cn.cnpayment.exception.ElementAlreadyExistException;
import com.cn.cnpayment.exception.InvalidInputException;
import com.cn.cnpayment.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentDetailsService {

    // Auto-wire the PaymentDetailsDAL object
    PaymentDetailsDAL repository;

    public PaymentDetailsService(PaymentDetailsDAL repository)
    {
        this.repository = repository;
    }

    @Transactional
    public PaymentDetails getPaymentDetailsById(int id) 
    {
        // 1. This method fetches PaymentDetails for the given id.
        PaymentDetails details = repository.getById(id);
        // 2. If no paymentDetails is found by the given id then it throws NotFoundException with custom message.
        if(details == null)
        {
            throw new NotFoundException("payment details not found");
        }
        return details;
    }

    @Transactional
    public List<PaymentDetails> getAllPaymentDetails()
    {
        // 1. This method fetches the list of all PaymentDetails from the database.
        return repository.getAllPaymentDetails();
    }

    @Transactional
    public void savePaymentDetails(PaymentDetails newPaymentDetails)
    {
        // 1. It first checks whether the given paymentDetails object exists in the database or not.
        PaymentDetails detail = repository.getById(newPaymentDetails.getId());
        // 2. If the given paymentDetails already exist in the database, then it throws ElementAlreadyExistException.
        if(detail != null)
        {
            throw new ElementAlreadyExistException("details already exists");
        }
        // 3. If the given paymentDetails doesn't exist, it saves the new PaymentDetails into the database.
        repository.save(newPaymentDetails);
    }

    @Transactional
    public void delete(int id)
    {
        // 1. It deletes a paymentDetails for the given id from the database.
        repository.delete(id);
    }

    @Transactional
    public void update(PaymentDetails paymentDetails)
    {
        // 1. It first checks if the given paymentDetails exists in the database or not.
        PaymentDetails detail = repository.getById(paymentDetails.getId());
        // 2. If the given paymentDetails object exists in the database, then it is simply updated.
        if(detail != null)
        {
            repository.update(paymentDetails);
        }
        // 3. If not found, then it throws NotFoundException with custom message.
        else
        {
            throw new NotFoundException("details not found");
        }
    }

    @Transactional
    public List<PaymentDetails> getByCurrency(String currency)
    {
        String[] currencies = {"inr", "rupee", "dollar", "yen", "pound", "usd"};
        boolean flag = false;
        for(String curr : currencies)
        {
            if(curr.equalsIgnoreCase(currency))
            {
                flag = true;
                break;
            }
        }
        if(!flag)
        {
            throw new InvalidInputException("invalid currency");
        }
        return repository.getByCurrency(currency);
    }
}
