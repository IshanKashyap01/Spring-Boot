package com.cn.cnEvent.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cn.cnEvent.entity.Ticket;
import com.cn.cnEvent.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController
{
    private TicketService service;

    public TicketController(TicketService service)
    {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id)
    {
        return service.getTicketById(id);
    }

    @GetMapping("/all")
    public List<Ticket> getAllTickets()
    {
        return service.getAllTickets();
    }

    @GetMapping("/allByAge/{age}")
    public List<Ticket> getTicketsBelowAge(@PathVariable Long age)
    {
        List<Ticket> allTickets = service.getAllTickets();
        List<Ticket> result = new ArrayList<>();
        for(Ticket ticket : allTickets)
        {
            if(ticket.getPerson().getAge() < age)
            {
                result.add(ticket);
            }
        }
        return result;
    }
}
