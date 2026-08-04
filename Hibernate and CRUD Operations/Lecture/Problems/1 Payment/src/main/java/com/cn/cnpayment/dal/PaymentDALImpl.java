package com.cn.cnpayment.dal;

import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cn.cnpayment.entity.Payment;
import com.cn.cnpayment.exception.ElementAlreadyExistException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

/*
Complete the PaymentDALImpl class as mentioned below:

Tasks:-

a. Autowire EntityManager.

b. Override the following methods:

1. getById(int id) : This method fetches the payment entity from
the database for a specific id.

2. getAllPayments() : This method fetches the list of payments from
the database.

3. getByPaymentType(String paymentType) : This method fetches the list
of payments from the database based on the paymentType received.

4. getByPaymentDescription(String keyword) : This method fetches the list
of payments from the database based on the keyword received.

5. addPayment(Payment payment) : This method saves a payment entity into the
database.

*/

@Repository
public class PaymentDALImpl implements PaymentDAL
{
	@Autowired
	EntityManager manager;

	@Transactional
	@Override
	public Payment getById(int id)
	{
		Session session = manager.unwrap(Session.class);
		return session.get(Payment.class, id);
	}

	@Transactional
	@Override
	public List<Payment> getAllPayments()
	{
		return manager.createQuery("Select p from Payment p", Payment.class).getResultList();
	}

	@Transactional
	@Override
	public List<Payment> getByPaymentType(String paymentType)
	{
		return manager.createQuery("Select p from Payment p where p.paymentType =:type", Payment.class)
		.setParameter("type", paymentType).getResultList();
	}

	@Transactional
	@Override
	public List<Payment> getByPaymentDescription(String keyword)
	{
		return manager.createQuery("Select p from Payment p where p.description like :desc", Payment.class)
		.setParameter("desc","%" + keyword + "%").getResultList();
	}

	@Transactional
	@Override
	public void addPayment(Payment payment)
	{
		Session session = manager.unwrap(Session.class);
		session.save(payment);
	}
}
