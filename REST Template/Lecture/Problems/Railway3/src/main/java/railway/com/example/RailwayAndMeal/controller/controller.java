package railway.com.example.RailwayAndMeal.controller;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import railway.com.example.RailwayAndMeal.Entity.Ticket;
import railway.com.example.RailwayAndMeal.customException.TicketBodyNotValidException;
import railway.com.example.RailwayAndMeal.service.RailwayService;

@RestController
@RequestMapping("/railway")
public class controller {

	private final RailwayService railwayService;

	controller(RailwayService railwayService)
	{
		this.railwayService = railwayService;
	}		
	
	@PostMapping("/ticket")
	public void addTicket( @RequestBody Ticket ticket, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			throw new TicketBodyNotValidException("Request Body violates restrictions");
		railwayService.addTicket(ticket);
	}
	
	
	@GetMapping("/tickets")
	public List<Ticket> getAllTickets(){
		return railwayService.getAllTickets();
	}
	
	@GetMapping("/ticket/{pnr}")
	public Ticket getTicketByPnr(@PathVariable long pnr) {
		return railwayService.getTicketByPnr(pnr);
	}
	
	@DeleteMapping("/ticket/{pnr}")
	public void deleteTicket(@PathVariable long pnr) {
		railwayService.deleteTicketByPnr(pnr);
	}
	
	@PutMapping("/ticket")
	public void updateTicket(@RequestBody Ticket ticket) {
		railwayService.updateTicket(ticket);
	}
	/**
	 * Complete the method body for the updateTicketPremium()
	 * Add required parameters with proper annotations.
	 */
	@PutMapping("/ticket/{pnr}/premium/{isPremium}")
	public void updateTicketPremium(@PathVariable long pnr, @PathVariable boolean isPremium)
	{
		railwayService.updateMealPremium(railwayService.getTicketByPnr(pnr), pnr, isPremium);
	}
}