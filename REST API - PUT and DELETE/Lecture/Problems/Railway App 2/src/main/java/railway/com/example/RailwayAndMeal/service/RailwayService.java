package railway.com.example.RailwayAndMeal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import railway.com.example.RailwayAndMeal.Entity.Ticket;

@Service
public class RailwayService {

	public List<Ticket> list = new ArrayList<>();
	public Map<Long,Ticket> ticketMap = new HashMap<>();

	public Ticket getTicketByPnr(long pnr) {
		return ticketMap.get(pnr);
	}

	public void addTicket(Ticket ticket) {
		list.add(ticket);
		ticketMap.put(ticket.getPnr(), ticket);
	}

	public List<Ticket> getAllTickets() {
		return list;
	}


	// Complete the method body to delete a ticket by PNR.
	public void deleteTicketByPnr(long pnr) 
	{
		if(ticketMap.containsKey(pnr))
		{
			Ticket toBeDelted = ticketMap.get(pnr);
			list.remove(toBeDelted);
			ticketMap.remove(pnr);
		}
	}

	
	// Complete the method body to update an existing ticket.
	public void updateTicket(Ticket ticket) 
	{
		deleteTicketByPnr(ticket.getPnr());
		addTicket(ticket);
	}
}