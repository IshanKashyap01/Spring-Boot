package com.cn.cnpayment.service;

import com.cn.cnpayment.dal.PaymentDAL;
import com.cn.cnpayment.entity.Payment;
import com.cn.cnpayment.exception.ElementAlreadyExistException;
import com.cn.cnpayment.exception.InvalidInputException;
import com.cn.cnpayment.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

	final PaymentDAL paymentDAL;

	PaymentService(PaymentDAL paymentDAL) {
		this.paymentDAL = paymentDAL;
	}

	@Transactional
	public Payment getPaymentById(int id) {

		Payment payment=paymentDAL.getById(id);

		if(payment==null)
		{
			throw new NotFoundException("No payment found with id:  "+id);
		}
		return payment;
	}

	@Transactional
	public List<Payment> getPaymentByPaymentType(String paymentType) {

		ArrayList<String> validPayments = new ArrayList<String>();
		validPayments.add("Cash");
		validPayments.add("Debit");
		validPayments.add("Credit");
		boolean isValidPayment=false;
		for(String validPayment : validPayments)
		{
			if(validPayment.equalsIgnoreCase(paymentType))
			{
				isValidPayment=true;
				break;
			}
		}
		if(!isValidPayment)
		{
			throw new InvalidInputException("Payment type "+ paymentType + "is incorrect");
		}
		List<Payment> payment = paymentDAL.getByPaymentType(paymentType);

		if(payment.isEmpty())
		{
			throw new NotFoundException("No payments found having paymentType: "+paymentType);
		}
		return payment;
	}

	@Transactional
	public List<Payment> getPaymentByDescriptionKeyword(String keyword) {

		List<Payment> payments = paymentDAL.getByPaymentDescription(keyword);
		if(payments.isEmpty())
		{
			throw new NotFoundException("No payments found, with description having keyword: "+keyword);
		}
		return payments;
	}

	@Transactional
	public List<Payment> getAllPayments() {

		return paymentDAL.getAllPayments();
	}

	@Transactional
	public void addPayment(Payment payment)  {
		if (paymentDAL.getById(payment.getId())!=null){
			throw new ElementAlreadyExistException("Payment already exists");
		}
		paymentDAL.addPayment(payment);
	}

	@Transactional
	public void update(Payment updatePayment) {
		paymentDAL.update(updatePayment);
	}

	@Transactional
	public void updateDescription(int id, String description) {
		paymentDAL.updateDescription(id,description);
	}

	@Transactional
	public void delete(int id) {
		paymentDAL.delete(id);
	}

	@Transactional
	public List<Payment> getAllPaymentsByCurrency(String currency)
	{
		/**
		 1. This method returns the list of payment by the given currency.
		 2. It only accepts the following currency in any format i.e. LowerCase/UpperCase.
		 3. "INR","Rupee","Dollar","Yen","Pound","USD".
		 4. It throws InvalidInputException if a currency different from above is received.
		 **/
		String[] currencies = {"inr", "rupee", "dollar", "yen", "pound", "usd"};
		for(String curr : currencies)
		{
			if(curr.equalsIgnoreCase(currency))
			{
				List<Payment> payments = paymentDAL.getAllPaymentsByCurrency(currency);
				if(payments.isEmpty())
				{
					throw new NotFoundException("not found");
				}
				return payments;
			}
		}
		throw new InvalidInputException("invalid currency " + currency);
	}
}

