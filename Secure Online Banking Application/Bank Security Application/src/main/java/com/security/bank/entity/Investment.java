package com.security.bank.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "investment")
public class Investment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private InvestmentType investmentType;

    private String risk;
    private double amount;
    private float returns;
    private String duration;
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}