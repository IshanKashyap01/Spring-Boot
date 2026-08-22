package com.cn.homeControlSystem.repositories;

import com.cn.homeControlSystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

//extend JpaRepository with relevant generic types.
//add annotation for this Repository interface.
public interface RoomRepository extends JpaRepository<Room, Integer>
{
}
