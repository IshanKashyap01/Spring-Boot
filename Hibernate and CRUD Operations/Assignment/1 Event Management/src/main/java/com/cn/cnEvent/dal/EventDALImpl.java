package com.cn.cnEvent.dal;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.cn.cnEvent.entity.Event;

@Repository
public class EventDALImpl implements EventDAL
{
    EntityManager manager;

    public EventDALImpl(EntityManager manager)
    {
        this.manager = manager;
    }

    @Override
    public Event getById(Long id)
    {
        Session session = manager.unwrap(Session.class);
        return session.get(Event.class, id);
    }

    @Override
    public List<Event> getAllEvents()
    {
        Session session = manager.unwrap(Session.class);
        return session.createQuery("Select e FROM Event e", Event.class)
        .getResultList();
    }

    @Override
    public void save(Event item)
    {
        Session session = manager.unwrap(Session.class);
        session.save(item);
    }

    @Override
    public void delete(long id)
    {
        Session session = manager.unwrap(Session.class);
        session.delete(getById(id));
    }

    @Override
    public void update(Event item)
    {
        Session session = manager.unwrap(Session.class);
        session.update(item);
    }
}