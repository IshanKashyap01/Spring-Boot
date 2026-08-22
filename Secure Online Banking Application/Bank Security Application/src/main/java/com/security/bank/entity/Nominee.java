package com.security.bank.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "nominee")
public class Nominee
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String relation;
    private String name;
    private Long accountNumber;
    private String gender;
    private int age;
}