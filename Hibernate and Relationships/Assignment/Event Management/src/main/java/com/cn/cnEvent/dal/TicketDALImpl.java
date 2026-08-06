package com.cn.cnEvent.dal;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.cn.cnEvent.entity.Ticket;

@Repository
public class TicketDALImpl implements TicketDAL
{
    private EntityManager manager;

    public TicketDALImpl(EntityManager manager)
    {
        this.manager = manager;
    }

    @Override
    public Ticket getTicketById(Long id)
    {
        Session session = manager.unwrap(Session.class);
        return session.get(Ticket.class, id);
    }

    @Override
    public List<Ticket> getAllTickets()
    {
        Session session = manager.unwrap(Session.class);
        return session.createQuery("from Ticket", Ticket.class)
        .getResultList();
    }

    @Override
    public void save(Ticket ticket)
    {
        Session session = manager.unwrap(Session.class);
        session.save(ticket);
    }
}