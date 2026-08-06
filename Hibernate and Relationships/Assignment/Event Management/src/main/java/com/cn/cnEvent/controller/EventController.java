package com.cn.cnEvent.controller;

import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.EventScheduleDetail;
import com.cn.cnEvent.entity.Ticket;
import com.cn.cnEvent.service.EventScheduleDetailService;
import com.cn.cnEvent.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController
{
	private final EventService eventService;
	private final EventScheduleDetailService detailService;

	EventController(EventService eventService, EventScheduleDetailService detailService)
	{
		this.eventService = eventService;
		this.detailService = detailService;
	}

	@GetMapping("/{id}")
	public Event getEventById(@PathVariable Long id)
	{
		return eventService.getEventById(id);
	}

	@GetMapping("/all")
	public List<Event> getAllEvents()
	{
		return eventService.getAllEvents();
	}

	@PostMapping("/save")
	public String saveEvent(@RequestBody Event event) {
		return eventService.saveEvent(event);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteEvent(@PathVariable Long id)
	{
		return eventService.delete(id);
	}

	@PutMapping("/update")
	public String updateEvent(@RequestBody Event updateEvent)
	{
		return eventService.update(updateEvent);
	}

	@GetMapping("/eventScheduleDetail/{id}")
	public EventScheduleDetail getDetails(@PathVariable long id)
	{
		Event event = getEventById(id);
		return detailService.get(event.getEventScheduleDetail().getId());
	}

	@DeleteMapping("/delete/eventScheduleDetail/{id}")
	public String deleteDetails(@PathVariable long id)
	{
		// Event event = getEventById(id);
		detailService.delete(id);
		return "The eventSchedule was deleted successfully";
	}

	@GetMapping("/location/{location}")
	public List<Event> getEventsByLocation(@PathVariable String location)
	{
		List<Event> allEvents = getAllEvents();
		List<Event> result = new ArrayList<>();
		for(Event event : allEvents)
		{
			if(event.getEventScheduleDetail().getLocation().equalsIgnoreCase(location))
			{
				result.add(event);
			}
		}
		return result;
	}

	@GetMapping("/allTickets/{id}")
	public List<Ticket> getAllTicketsOfEvent(@PathVariable Long id)
	{
		return eventService.getEventById(id).getTickets();
	}

	@GetMapping("/tickets/PriceGreaterThan/{price}")
	public List<Event> getTicketsPricierThan(@PathVariable Long price)
	{
		List<Event> allEvents = eventService.getAllEvents();
		List<Event> result = new ArrayList<>();
		for(Event event : allEvents)
		{
			for(Ticket ticket : event.getTickets())
			{
				if(ticket.getPrice() > price)
				{
					result.add(event);
					break;
				}
			}
		}
		return result;
	}
}
