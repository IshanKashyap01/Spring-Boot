package com.cn.cnEvent.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.cn.cnEvent.dal.TicketDAL;
import com.cn.cnEvent.entity.Ticket;
import com.cn.cnEvent.exception.ElementAlreadyExistException;
import com.cn.cnEvent.exception.NotFoundException;

@Service
public class TicketService
{
    private TicketDAL dal;

    public TicketService(TicketDAL dal)
    {
        this.dal = dal;
    }

    @Transactional
    public Ticket getTicketById(Long id)
    {
        Ticket ticket = dal.getTicketById(id);
        if(ticket == null)
        {
            throw new NotFoundException("ticket not found");
        }
        return ticket;
    }

    @Transactional
    public List<Ticket> getAllTickets()
    {
        return dal.getAllTickets();
    }

    @Transactional
    public void saveTicket(Ticket ticket)
    {
        if(dal.getTicketById(ticket.getId()) != null)
        {
            throw new ElementAlreadyExistException("ticket already exists");
        }
        dal.save(ticket);
    }
}
