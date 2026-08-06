package com.cn.cnEvent.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.cn.cnEvent.dal.PersonDAL;
import com.cn.cnEvent.entity.Person;
import com.cn.cnEvent.exception.ElementAlreadyExistException;
import com.cn.cnEvent.exception.NotFoundException;

@Service
public class PersonService
{
    private PersonDAL dal;

    public PersonService(PersonDAL dal)
    {
        this.dal = dal;
    }

    @Transactional
    public Person getPersonById(Long id)
    {
        Person person = dal.getPersonById(id);
        if(person == null)
        {
            throw new NotFoundException("person not found");
        }
        return person;
    }

    @Transactional
    public List<Person> getAllPersons()
    {
        return dal.getAllPersons();
    }

    @Transactional
    public void savePerson(Person person)
    {
        if(dal.getPersonById(person.getId()) != null)
        {
            throw new ElementAlreadyExistException("person already exists");
        }
        dal.save(person);
    }
}
