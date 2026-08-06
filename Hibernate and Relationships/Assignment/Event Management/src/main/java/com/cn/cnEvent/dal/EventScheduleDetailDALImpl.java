package com.cn.cnEvent.dal;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.EventScheduleDetail;

@Repository
public class EventScheduleDetailDALImpl implements EventScheduleDetailDAL
{
    private EntityManager manager;

    public EventScheduleDetailDALImpl(EntityManager manager)
    {
        this.manager = manager;
    }

    @Override
    public EventScheduleDetail get(long id)
    {
        Session session = manager.unwrap(Session.class);
        return session.get(EventScheduleDetail.class, id);
    }

    @Override
    public List<EventScheduleDetail> getAll()
    {
        Session session = manager.unwrap(Session.class);
        return session.createQuery("from EventScheduleDetail", EventScheduleDetail.class)
        .getResultList();
    }

    @Override
    public void save(EventScheduleDetail details)
    {
        Session session = manager.unwrap(Session.class);
        session.save(details);
    }

    @Override
    public void delete(long id)
    {
        Session session = manager.unwrap(Session.class);
        Event event = session.get(Event.class, id);
        if(event != null && event.getEventScheduleDetail() != null)
        {
            EventScheduleDetail detail = event.getEventScheduleDetail();
            event.setEventScheduleDetail(null);
            session.save(event);
            session.delete(detail);
        }
        session.delete(get(id));
    }
}