package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Event;

import java.util.List;

public interface EventDAL
{
	Event getById(Long id);

	List<Event> getAllEvents();

	void save(Event item);

	void delete(long id);

	void update(Event item);
}