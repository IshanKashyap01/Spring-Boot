package com.cn.homeControlSystem.model;


import jakarta.persistence.*;
import lombok.*;

/**
 * Add lombok annotations for auto generating constructors, getters and setters.
 * Add proper annotations to make this class as entity.
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "smart_device")
public class SmartDevice
{
    //add proper annotations for mapping a property as id.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private String status;
    private Integer roomId;
}