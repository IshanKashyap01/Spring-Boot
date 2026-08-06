package com.cn.cnpayment.controller;

import org.springframework.web.bind.annotation.*;
import com.cn.cnpayment.entity.*;
import com.cn.cnpayment.service.PaymentService;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	final PaymentService paymentService;

	PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@GetMapping("/id/{id}")
	public Payment getPaymentById(@PathVariable int id)
	{
		return paymentService.getPaymentById(id);
	}

	@GetMapping("/allPayments")
	public List<Payment> getAllPayments()
	{
		return paymentService.getAllPayments();
	}

	@GetMapping("/paymentType/{paymentType}")
	public List<Payment> getPaymentByPaymentType(@PathVariable String paymentType)
	{
		return paymentService.getPaymentByPaymentType(paymentType);
	}
	@GetMapping("/description/{keyword}")
	public List<Payment> getPaymentByDescriptionKeyword(@PathVariable String keyword)
	{
		return paymentService.getPaymentByDescriptionKeyword(keyword);
	}
	@PostMapping("/save")
	public void addPayment(@RequestBody Payment payment){
		paymentService.addPayment(payment);
	}

	@DeleteMapping("/delete/id/{id}")
	public void deletePayment(@PathVariable int id)
	{
		paymentService.delete(id);
	}

	@PutMapping("/update")
	public void updatePayment(@RequestBody Payment payment)
	{
		paymentService.update(payment);
	}

	@PutMapping("/update/{id}/description/{description}")
	public void updateDescription(@PathVariable("id") int id, @PathVariable("description") String description)
	{
		paymentService.updateDescription(id,description);
	}

	@GetMapping("/currency/{currency}")
	public List<Payment> getPaymentsByCurrency(@PathVariable String currency)
	{
		return paymentService.getAllPaymentsByCurrency(currency);
	}


	// a. GET /payment/reviews/{paymentId}: It fetches the list of all PaymentReview associated with the given payment Id.
	@GetMapping("/reviews/{paymentId}")
	public List<PaymentReview> getPaymentReviews(@PathVariable int paymentId)
	{
		return paymentService.getPaymentReviews(paymentId);
	}

	// a. GET /payment/queryType/{queryType}: It fetches the list of all payment for the given query type.
	@GetMapping("/queryType/{queryType}")
	public List<Payment> getPaymentsByQueryType(@PathVariable String queryType)
	{
		return paymentService.getAllPaymentsByQueryType(queryType);
	}

}
