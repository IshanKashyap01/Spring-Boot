package com.cn.cnpayment.dal;

import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.cn.cnpayment.entity.PaymentReview;
import jakarta.persistence.EntityManager;

@Repository
public class PaymentReviewDALImpl implements PaymentReviewDAL
{
	EntityManager manager;

	public PaymentReviewDALImpl(EntityManager manager)
	{
		this.manager = manager;
	}

	@Override
	public PaymentReview getById(int id)
	{
		Session session = manager.unwrap(Session.class);
		return session.get(PaymentReview.class, id);
	}

	@Override
	public void save(PaymentReview paymentDetails)
	{
		Session session = manager.unwrap(Session.class);
		session.save(paymentDetails);
	}

	@Override
	public void delete(int id)
	{
		Session session = manager.unwrap(Session.class);
		PaymentReview toBeDeleted = getById(id);
		session.delete(toBeDeleted);
	}

	@Override
	public List<PaymentReview> getAllPaymentReview()
	{
		Session session = manager.unwrap(Session.class);
		return session.createQuery("select r from PaymentReview r", PaymentReview.class)
		.getResultList();
	}

	@Override
	public List<PaymentReview> getByQueryType(String queryType)
	{
		Session session = manager.unwrap(Session.class);
		return session.createQuery("select r from PaymentReview r where queryType =:type", PaymentReview.class)
		.setParameter("type", queryType)
		.getResultList();
	}
}
