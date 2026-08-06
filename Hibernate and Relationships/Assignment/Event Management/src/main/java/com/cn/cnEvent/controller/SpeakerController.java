package com.cn.cnEvent.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.cn.cnEvent.dal.EventDAL;
import org.springframework.web.bind.annotation.*;
import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.Speaker;
import com.cn.cnEvent.exception.ElementAlreadyExistException;
import com.cn.cnEvent.exception.NotFoundException;
import com.cn.cnEvent.service.EventService;
import com.cn.cnEvent.service.SpeakerService;

@RestController
@RequestMapping("/speaker")
public class SpeakerController {
    private SpeakerService service;
    private EventService eventService;
    private EventDAL eventDAL;

    public SpeakerController(SpeakerService service, EventService eventService,  EventDAL eventDAL) {
        this.service = service;
        this.eventService = eventService;
        this.eventDAL = eventDAL;
    }

    @GetMapping("/{id}")
    public Speaker getSpeakerById(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/all")
    public List<Speaker> getAllSpeakers() {
        return service.getAll();
    }

    @GetMapping("/eventCount/{eventCount}/experience/{experience}")
    public List<Speaker> getSpeakersWithMoreEventsAndExperience(@PathVariable Long eventCount, @PathVariable Long experience) {
        List<Speaker> speakers = getAllSpeakers();
        List<Speaker> result = new ArrayList<>();
        for (Speaker speaker : speakers) {
            if (speaker.getEvents().size() >= eventCount && speaker.getExperience() > experience) {
                result.add(speaker);
            }
        }
        return result;
    }

    @Transactional
    @PostMapping("/id/{id}/eventId/{eventId}")
    public void addNewEventToSpeaker(@PathVariable Long id, @PathVariable Long eventId) {
        Speaker speaker = getSpeakerById(id);
        Event event = eventService.getEventById(eventId);
        if (speaker == null) {
            throw new NotFoundException("speaker not found");
        }
        if (event == null) {
            throw new NotFoundException("event not found");
        }
        if (speaker.getEvents().contains(event)) {
            throw new ElementAlreadyExistException("speaker already added to event");
        }
//        speaker.getEvents().add(event);
//        service.save(speaker);
        event.getSpeakers().add(speaker);
        eventDAL.save(event);
    }

    @PostMapping("/save")
    public String saveSpeaker(@RequestBody Speaker speaker) {
        service.save(speaker);
        return "The speaker was saved successfully.";
    }
}
