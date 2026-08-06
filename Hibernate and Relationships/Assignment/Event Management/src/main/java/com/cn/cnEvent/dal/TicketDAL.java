package com.cn.cnEvent.dal;

import java.util.List;
import com.cn.cnEvent.entity.Ticket;

public interface TicketDAL
{
    Ticket getTicketById(Long id);

    List<Ticket> getAllTickets();

    void save(Ticket ticket);
}
