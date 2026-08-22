package Telecom.SubscriptionService.model;

import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private BigInteger contact;
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private Account account;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Subscription> subscriptionList = new ArrayList<>();
}