package com.cn.cnEvent.dal;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.cn.cnEvent.entity.Person;

@Repository
public class PersonDALImpl implements PersonDAL
{
    private EntityManager manager;

    public PersonDALImpl(EntityManager manager)
    {
        this.manager = manager;
    }

    @Override
    public Person getPersonById(Long id)
    {
        Session session = manager.unwrap(Session.class);
        return session.get(Person.class, id);
    }

    @Override
    public List<Person> getAllPersons()
    {
        Session session = manager.unwrap(Session.class);
        return session.createQuery("from Person", Person.class)
        .getResultList();
    }

    @Override
    public void save(Person person)
    {
        Session session = manager.unwrap(Session.class);
        session.save(person);
    }
}