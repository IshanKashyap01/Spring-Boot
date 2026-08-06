package com.cn.cnEvent.dal;

import java.util.List;
import com.cn.cnEvent.entity.Person;

public interface PersonDAL
{
    Person getPersonById(Long id);

    List<Person> getAllPersons();

    void save(Person person);
}
