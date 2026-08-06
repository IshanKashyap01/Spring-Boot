package com.cn.cnEvent.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.cn.cnEvent.entity.EventScheduleDetail;
import com.cn.cnEvent.service.EventScheduleDetailService;

@RestController
@RequestMapping("/eventScheduleDetail")
public class EventScheduleDetailController
{
    private EventScheduleDetailService service;

    public EventScheduleDetailController(EventScheduleDetailService service)
    {
        this.service = service;
    }

    @GetMapping("/{id}")
    public EventScheduleDetail getById(@PathVariable long id)
    {
        return service.get(id);
    }

    @GetMapping("/all")
    public List<EventScheduleDetail> getAll()
    {
        return service.getAll();
    }

    @PostMapping(value = "/save", consumes = "application/json")
    public String saveDetails(@RequestBody EventScheduleDetail details)
    {
        service.save(details);
        return "saved successfully";
    }
}