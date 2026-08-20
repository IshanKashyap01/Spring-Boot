package com.cn.homeControlSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/**
 * Add lombok annotations for auto generating constructors, getters and setters.
 * Add proper annotations to make this class as entity.
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room
{
    //add proper annotations for mapping a property as id.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
}