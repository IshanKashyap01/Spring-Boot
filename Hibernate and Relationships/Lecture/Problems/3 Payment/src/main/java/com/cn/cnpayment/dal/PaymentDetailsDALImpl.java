package com.cn.cnpayment.dal;

import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.cn.cnpayment.entity.PaymentDetails;
import jakarta.persistence.EntityManager;

/**
 # Complete the PaymentDetailsDALImpl class as mentioned below:

 	a. Autowire EntityManager.

 	b. Override the following methods:

 		1. getById(int id): This method fetches PaymentDetails for a specific id from the database.

	 	2. getAllPaymentDetails(): This method fetches the list of PaymentDetails from the database.

	 	3. save(PaymentDetails paymentDetails): This method saves the PaymentDetails entity into the database.

	 	4. delete(int id): This method deletes the PaymentDetails entity for a specific id.

	 	5. update(PaymentDetails paymentDetails): This method updates paymentDetails.

	 	6. getByCurrency(String currency): This method fetches the list of PaymentDetails from the database for
                                           a specific currency.
 **/


@Repository
public class PaymentDetailsDALImpl implements PaymentDetailsDAL
{
	// Auto-wire the EntityManager object
	EntityManager manager;

	public PaymentDetailsDALImpl(EntityManager manager)
	{
		this.manager = manager;
	}

	@Override
	public PaymentDetails getById(int id)
	{
		Session session = manager.unwrap(Session.class);
		return session.get(PaymentDetails.class, id);
	}

	@Override
	public void save(PaymentDetails paymentDetails)
	{
		Session session = manager.unwrap(Session.class);
		session.save(paymentDetails);
	}

	@Override
	public void delete(int id)
	{
		Session session = manager.unwrap(Session.class);
		session.delete(getById(id));
	}

	@Override
	public List<PaymentDetails> getAllPaymentDetails()
	{
		Session session = manager.unwrap(Session.class);
		return session.createQuery("from PaymentDetails", PaymentDetails.class)
		.getResultList();
	}

	@Override
	public void update(PaymentDetails paymentDetails)
	{
		Session session = manager.unwrap(Session.class);
		PaymentDetails curr = getById(paymentDetails.getId());
		curr.setAmount(paymentDetails.getAmount());
		curr.setCreditAccount(paymentDetails.getCreditAccount());
		curr.setCurrency(paymentDetails.getCurrency());
		curr.setDebitAccount(paymentDetails.getDebitAccount());
		curr.setPayment(paymentDetails.getPayment());
		session.update(curr);
	}

	@Override
	public List<PaymentDetails> getByCurrency(String currency)
	{
		Session session = manager.unwrap(Session.class);
		return session.createQuery("select d from PaymentDetails d where currency=:curr", PaymentDetails.class)
		.setParameter("curr", currency)
		.getResultList();
	}
}
