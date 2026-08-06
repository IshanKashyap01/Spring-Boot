package com.cn.cnEvent.dal;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.cn.cnEvent.entity.Speaker;

@Repository
public class SpeakerDALImpl implements SpeakerDAL
{
    private EntityManager manager;

    public SpeakerDALImpl(EntityManager manager)
    {
        this.manager = manager;
    }

    @Override
    public Speaker get(Long id)
    {
        Session session = manager.unwrap(Session.class);
        return session.get(Speaker.class, id);
    }

    @Override
    public List<Speaker> getAll()
    {
        Session session = manager.unwrap(Session.class);
        return session.createQuery("from Speaker", Speaker.class)
        .getResultList();
    }

    @Override
    public void save(Speaker speaker)
    {
        Session session = manager.unwrap(Session.class);
        session.save(speaker);
    }
}
