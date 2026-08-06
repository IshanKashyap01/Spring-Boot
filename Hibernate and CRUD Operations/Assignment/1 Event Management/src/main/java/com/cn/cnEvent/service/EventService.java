package com.cn.cnEvent.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.cn.cnEvent.dal.EventDAL;
import com.cn.cnEvent.entity.Event;

@Service
public class EventService
{
    private EventDAL eventRepository;

    public EventService(EventDAL eventRepository)
    {
        this.eventRepository = eventRepository;
    }
    @Transactional
    public Event get(long id)
    {
        return eventRepository.getById(id);
    }
    @Transactional
    public List<Event> getAll()
    {
        return eventRepository.getAllEvents();
    }
    @Transactional
    public void add(Event event)
    {
        eventRepository.save(event);
    }
    @Transactional
    public void delete(long id)
    {
        eventRepository.delete(id);
    }
    @Transactional 
    public void update(Event event)
    {
        eventRepository.update(event);
    }
}
