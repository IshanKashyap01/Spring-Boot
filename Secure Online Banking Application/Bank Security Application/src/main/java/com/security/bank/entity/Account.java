package com.security.bank.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "account")
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String status;
    private double balance;
    private float interestRate;

    @Enumerated(EnumType.STRING)
    private BranchType branch;

    private String proof;
    private Date openingDate;
    
    @Column(nullable = false, unique = true)
    private Long accountNumber;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nominee_id")
    private Nominee nominee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}