package com.security.bank.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "card")
public class Card
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long cardNumber;

    private String cardHolderName;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    private double dailyLimit;
    private int cvv;
    private Date allocationDate;
    private Date expiryDate;
    private Long pin;
    private String status;

    public void setCardTypeAndDailyLimit(CardType cardType, double dailyLimit)
    {
        this.cardType = cardType;
        this.dailyLimit = dailyLimit;
    }
}