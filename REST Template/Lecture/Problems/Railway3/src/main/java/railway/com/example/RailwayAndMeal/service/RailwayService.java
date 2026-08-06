package railway.com.example.RailwayAndMeal.service;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import railway.com.example.RailwayAndMeal.Entity.*;
import railway.com.example.RailwayAndMeal.communicator.MealServiceCommunicator;
import railway.com.example.RailwayAndMeal.customException.*;

@Service
public class RailwayService {
	
	final MealServiceCommunicator mealServiceCommunicator;
	
	public List<Ticket> list = new ArrayList<>();
	public Map<Long,Ticket> ticketMap = new HashMap<>();

	RailwayService(MealServiceCommunicator mealServiceCommunicator) {
		this.mealServiceCommunicator = mealServiceCommunicator;
	}
	
	public Ticket getTicketByPnr(long pnr) {
		if(ObjectUtils.isEmpty(ticketMap.get(pnr)))
			throw new TicketNotFoundException("Ticket by given PNR does not exist");
		
		Ticket ticket = ticketMap.get(pnr);	
		
		Meal meal = mealServiceCommunicator.getMealByPnr(pnr);
		ticket.setMeal(meal);
		
		return ticket;
	}
	
	public void addTicket(Ticket ticket) {
		ticket.setMeal(new Meal(ticket.getPnr()));
		
		mealServiceCommunicator.setMeal(ticket.getMeal());
		
		list.add(ticket);
		ticketMap.put(ticket.getPnr(), ticket);
	}
	
	public List<Ticket> getAllTickets() {		
		return list;
	}
	/** Complete the "deleteTicket()" method by calling the "deleteMeal()" 
	    method of MealServiceCommunicator" class.
	**/
	public void deleteTicketByPnr(long pnr)
	{
		Ticket ticket = this.getTicketByPnr(pnr);		
		list.remove(ticket);
		ticketMap.remove(ticket.getPnr());
		mealServiceCommunicator.deleteMeal(pnr);
	}

	/**
	Complete the "updateTicket()" method by calling the "updateMeal()" method of 
	MealServiceCommunicator" class.
	**/
	public void updateTicket(Ticket ticket)
	{
		Ticket existingTicket = this.getTicketByPnr(ticket.getPnr());
		list.remove(existingTicket);
		ticketMap.remove(existingTicket.getPnr());
		list.add(ticket);
		ticketMap.put(ticket.getPnr(), ticket);
		mealServiceCommunicator.updateMeal(ticket.getMeal());
	}
	
	/*
	 * 1. Create a service function to handle UpdateTicketPremium method from Controller.
	 * 2. The service method should update the premium of the given ticket's meal.
	 * 3. The service method should also make a call to the mealServiceCommunicator to update
	 * 		the meal in the meal service.
	 */
	public void updateMealPremium(Ticket ticket, Long pnr ,boolean isPremium)
	{
		if( ticket.getPnr() != pnr)
		{
			throw new TicketBodyNotValidException("Pnr does not match");
		}
		//write code here
		Meal meal = ticket.getMeal();
		meal.setPremium(isPremium);
		mealServiceCommunicator.updateMeal(meal);
	}
}