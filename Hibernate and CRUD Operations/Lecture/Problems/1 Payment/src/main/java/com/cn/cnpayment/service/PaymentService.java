package com.cn.cnpayment.service;

import org.springframework.stereotype.Service;
import com.cn.cnpayment.dal.PaymentDAL;
import com.cn.cnpayment.entity.Payment;
import com.cn.cnpayment.exception.ElementAlreadyExistException;
import com.cn.cnpayment.exception.InvalidInputException;
import com.cn.cnpayment.exception.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 Complete the PaymentService class as mentioned below:

Tasks:-

a. Autowire PaymentDAL.

b. Complete the following methods:

1. getPaymentById(int id): This method fetches payment from the dal
layer for a specific id.

2. getPaymentByPaymentType(String paymentType): This method
fetches a list of Payment from the dal layer based on the paymentType received.

3. getPaymentByDescriptionKeyword(String keyword): This method
fetches a list of payments from the dal layer based on the keyword received.

4. getAllPayments(): This method fetches a list of payments
from the dal layer.

5. addPayment(Payment payment): This method saves payment entity into the
database using the dal layer.
**/

@Service
public class PaymentService 
{
	// Auto-wire necessary DAl layer object;
	final PaymentDAL paymentDAL;

	PaymentService(PaymentDAL paymentDAL)
	{
		this.paymentDAL = paymentDAL;
	}

	@Transactional
	public Payment getPaymentById(int id)
	{
		Payment payment = paymentDAL.getById(id);
		if(payment == null)
		{
			throw new NotFoundException("payment with id " + id + " not found");
		}
		return payment;
	}
	@Transactional
	public List<Payment> getPaymentByPaymentType(String paymentType)
	{
		if(!(paymentType.equals("Credit") || paymentType.equals("Debit") || paymentType.equals("Cash")))
		{
			throw new InvalidInputException("invalid payment type");
		}
		return paymentDAL.getByPaymentType(paymentType);
	}
	@Transactional
	public List<Payment> getPaymentByDescriptionKeyword(String keyword)
	{
		return paymentDAL.getByPaymentDescription(keyword);
	}
	@Transactional
	public List<Payment> getAllPayments()
	{
		return paymentDAL.getAllPayments();
	}
	@Transactional
	public void addPayment(Payment payment)
	{
		if(this.paymentDAL.getById(payment.getId()) != null)
		{
			throw new ElementAlreadyExistException("Payment with id " + payment.getId() + " already exists");
		}
		paymentDAL.addPayment(payment);
	}
}