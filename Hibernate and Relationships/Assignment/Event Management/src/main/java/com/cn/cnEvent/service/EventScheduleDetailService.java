package com.cn.cnEvent.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.cn.cnEvent.dal.EventScheduleDetailDAL;
import com.cn.cnEvent.entity.EventScheduleDetail;
import com.cn.cnEvent.exception.ElementAlreadyExistException;
import com.cn.cnEvent.exception.NotFoundException;

@Service
public class EventScheduleDetailService
{
    private EventScheduleDetailDAL dal;

    public EventScheduleDetailService(EventScheduleDetailDAL dal)
    {
        this.dal = dal;
    }
    @Transactional
    public EventScheduleDetail get(long id)
    {
        EventScheduleDetail details = dal.get(id);
        if(details == null)
        {
            throw new NotFoundException("event details not found");
        }
        return details;
    }
    @Transactional
    public List<EventScheduleDetail> getAll()
    {
        return dal.getAll();
    }
    @Transactional
    public void save(EventScheduleDetail details)
    {
        if(details.getId() != null && dal.get(details.getId()) != null)
        {
            throw new ElementAlreadyExistException("details already exists");
        }
        dal.save(details);
    }
    @Transactional
    public void delete(long id)
    {
        EventScheduleDetail details = dal.get(id);
        if(details == null)
        {
            throw new NotFoundException("event details not found");
        }
        dal.delete(id);
    }
}
