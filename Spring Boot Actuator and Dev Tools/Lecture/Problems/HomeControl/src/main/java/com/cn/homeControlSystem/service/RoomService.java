package com.cn.homeControlSystem.service;

import com.cn.homeControlSystem.model.Room;
import com.cn.homeControlSystem.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService
{
    //autowire the RoomRepository object;
    private final RoomRepository repository;
    /**
     1. Complete the method body to fetch all room records.
     **/
    public List<Room> getAllRooms()
    {
        return repository.findAll();
    }
    /**
     1. Complete the method body to fetch a room record by id.
     **/
    public Room getRoomById(Integer id)
    {
        return repository.findById(id).get();
    }
    /**
     1. Complete the method body to save a room record.
     **/
    public void addRoom(Room room)
    {
        repository.save(room);
    }

    /**
     1. Complete the method body to delete a room record.
     **/
    public void deleteRoom(Integer id)
    {
        Room room = getRoomById(id);
        repository.delete(room);
    }
}
