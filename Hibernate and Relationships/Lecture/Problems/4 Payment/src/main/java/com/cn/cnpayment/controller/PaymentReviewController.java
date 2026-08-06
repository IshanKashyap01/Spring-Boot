package com.cn.cnpayment.controller;

import com.cn.cnpayment.entity.PaymentReview;
import com.cn.cnpayment.service.PaymentReviewService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 # In the PaymentReviewController class complete the methods to handle HTTP requests with the required
 annotation for the following APIs:
**/
@RestController
@RequestMapping("/review")
public class PaymentReviewController 
{
	// Autowire the PaymentReviewService object.
	PaymentReviewService service;

	public PaymentReviewController(PaymentReviewService service)
	{
		this.service = service;
	}

	// a. GET "/review/id/{id}": It fetches PaymentReview for a specific id.
	@GetMapping("/id/{id}")
	public PaymentReview getPaymentReviewById(@PathVariable int id)
	{
		return service.getPaymentReviewById(id);
	}

	// b. POST "/review/save": It saves PaymentReview in the database.
	@PostMapping("/save")
	public void savePaymentReview(@RequestBody PaymentReview paymentReview)
	{
		service.savePaymentReview(paymentReview);
	}


	// c. DELETE "/review/id/{id}": It deletes a PaymentReview for a specific id.
	@DeleteMapping("/id/{id}")
	public void delete(@PathVariable int id)
	{
		service.delete(id);
	}


	// d. GET "/review/queryType/{queryType}": It fetches the list of all payment reviews containing
	//                                         the given query type from the database.
	@GetMapping("/queryType/{queryType}")
	public List<PaymentReview> getReviewsByQueryType(@PathVariable String queryType)
	{
		return service.getPaymentReviewByQueryType(queryType);
	}


	// e. GET "/review/allReviews": It fetches the list of all payment reviews from the database.
	@GetMapping("/allReviews")
	public List<PaymentReview> getAllReviews()
	{
		return service.getAllPaymentReviews();
	}
}
