package railway.com.example.RailwayAndMeal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import railway.com.example.RailwayAndMeal.Entity.Ticket;
import railway.com.example.RailwayAndMeal.exceptions.TicketAlreadyExistsException;
import railway.com.example.RailwayAndMeal.exceptions.TicketNotFoundException;

@Service
public class RailwayService {

	public List<Ticket> list = new ArrayList<>();
	public Map<Long,Ticket> ticketMap = new HashMap<>();

	public Ticket getTicketByPnr(long pnr)
	{
		/**
		 1. Implement the logic to first check whether the ticket with given
		pnr exist or not.
		2. If no ticket is found by the given pnr number then throw TicketNotFoundException with
		custom message.
		3. If ticket is found then we simply return it.
		**/
		if(!ticketMap.containsKey(pnr))
		{
			throw new TicketNotFoundException("No ticket with pnr " + pnr + " found");
		}
		return ticketMap.get(pnr);
	}
	
	public void addTicket(Ticket ticket) 
	{
		/**
		1. Before adding the ticket, now you need to check whether the given ticket with the same pnr
		number exists or not.
		2. If a ticket with same pnr is found then we throw TicketAlreadyExistsException with custom message.
		3. If no ticket is found then we simply add it.
		**/
		long pnr = ticket.getPnr();
		if(ticketMap.containsKey(pnr))
		{
			throw new TicketAlreadyExistsException("Ticket with pnr " + pnr + " already exists");
		}
		list.add(ticket);
		ticketMap.put(pnr, ticket);	
	}
	
	public List<Ticket> getAllTickets() {		
		return list;
	}
	
	public void deleteTicketByPnr(long pnr)
	{
		if(!ticketMap.containsKey(pnr))
		{
			throw new TicketNotFoundException("No ticket with pnr " + pnr + " found");
		}
		Ticket ticket = this.getTicketByPnr(pnr);
		list.remove(ticket);
		ticketMap.remove(ticket.getPnr());
	}
	
	public void updateTicket(Ticket ticket) 
	{
		if(!ticketMap.containsKey(ticket.getPnr()))
		{
			throw new TicketNotFoundException("No ticket with pnr " + ticket.getPnr() + " found");
		}
		Ticket existingTicket = this.getTicketByPnr(ticket.getPnr());
		
		list.remove(existingTicket);
		ticketMap.remove(existingTicket.getPnr());
		
		list.add(ticket);
		ticketMap.put(ticket.getPnr(), ticket);
	}
}