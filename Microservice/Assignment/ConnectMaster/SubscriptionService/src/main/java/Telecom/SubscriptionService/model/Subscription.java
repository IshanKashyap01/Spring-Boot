package Telecom.SubscriptionService.model;

import lombok.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@Table(name = "subscription")
public class Subscription
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer price;
    private String planName;
    private String planDetails;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("subscriptionList")
    private User user;
}