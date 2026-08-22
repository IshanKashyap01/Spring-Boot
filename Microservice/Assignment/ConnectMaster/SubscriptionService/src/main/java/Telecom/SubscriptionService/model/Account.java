package Telecom.SubscriptionService.model;

import lombok.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String balance;
    private String details;

    @OneToOne(mappedBy = "account")
    @JsonIgnoreProperties("account")
    private User user;
}