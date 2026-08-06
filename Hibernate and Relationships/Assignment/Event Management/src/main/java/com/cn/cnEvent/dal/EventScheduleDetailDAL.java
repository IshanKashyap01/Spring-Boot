package com.cn.cnEvent.dal;

import java.util.List;

import com.cn.cnEvent.entity.EventScheduleDetail;

public interface EventScheduleDetailDAL 
{
    EventScheduleDetail get(long id);

    List<EventScheduleDetail> getAll();

    void save(EventScheduleDetail details);

    void delete(long id);
}
