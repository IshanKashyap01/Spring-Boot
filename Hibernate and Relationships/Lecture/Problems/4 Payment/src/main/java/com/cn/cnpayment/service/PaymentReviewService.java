package com.cn.cnpayment.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.cn.cnpayment.dal.PaymentReviewDAL;
import com.cn.cnpayment.entity.PaymentReview;
import com.cn.cnpayment.exception.ElementAlreadyExistException;
import com.cn.cnpayment.exception.InvalidInputException;
import com.cn.cnpayment.exception.NotFoundException;
import java.util.List;

@Service
public class PaymentReviewService
{
	// Autowire the PaymentReviewDAL object.
	PaymentReviewDAL dal;

	public PaymentReviewService(PaymentReviewDAL dal)
	{
		this.dal = dal;
	}

	@Transactional
	public PaymentReview getPaymentReviewById(int id)
	{
		/**
		   1. This method fetches PaymentReview for a specific id.
		   2. If no paymentReview is found then it throws NotFoundException.
		**/
		PaymentReview review = dal.getById(id);
		if(review == null)
		{
			throw new NotFoundException("payment review not found");
		}
		return review;
	}

	@Transactional
	public List<PaymentReview> getAllPaymentReviews()
	{
		/**
		 1. This method fetches the list of all PaymentReviews.
		 2. If no paymentReview is found then it throws NotFoundException.
		 **/
		return dal.getAllPaymentReview();
	}

	@Transactional
	public void savePaymentReview(PaymentReview newPaymentReview)
	{
		/**
		 1. This method first checks if the given paymentReview exists or not.
		 2. If the given paymentReview is not found, then it saves the PaymentReview entity into the database.
		 3. If found then it throws ElementAlreadyExistException.
		 **/
		if(dal.getById(newPaymentReview.getId()) != null)
		{
			throw new ElementAlreadyExistException("review already exists");
		}
		dal.save(newPaymentReview);
	}

	@Transactional
	public void delete(int id)
	{
		/**
		 1. This method deletes PaymentReview for a specific id.
		 2. If no paymentReview is found for the given id, then it throws NotFoundException.
		 **/
		if(dal.getById(id) == null)
		{
			throw new NotFoundException("payment review not found");
		}
		dal.delete(id);
	}

	@Transactional
	public List<PaymentReview> getPaymentReviewByQueryType(String queryType)
	{
		List<PaymentReview> reviewsByQueryType = dal.getByQueryType(queryType);

		if (reviewsByQueryType.isEmpty()){
			throw new InvalidInputException("Invalid Currency");
		}
		return reviewsByQueryType;
	}

}
