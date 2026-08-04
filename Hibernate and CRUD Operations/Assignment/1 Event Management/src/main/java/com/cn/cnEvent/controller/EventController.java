package com.cn.cnEvent.controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.service.EventService;


@RestController
@RequestMapping("/event")
public class EventController
{
    private EventService serivce;

    public EventController(EventService service)
    {
        this.serivce = service;
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable long id) 
    {
        return serivce.get(id);
    }

    @GetMapping("/all")
    public List<Event> getAllEvents()
    {
        return serivce.getAll();
    }

    @PostMapping("/save")
    public void addEvent(@RequestBody Event event)
    {
        serivce.add(event);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable long id)
    {
        serivce.delete(id);
    }

    @PutMapping("/update")
    public void updateEvent(@RequestBody Event event)
    {
        serivce.update(event);
    }
}
