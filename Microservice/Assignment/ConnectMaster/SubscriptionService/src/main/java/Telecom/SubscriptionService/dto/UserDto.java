package Telecom.SubscriptionService.dto;

import java.math.BigInteger;
import java.util.*;
import Telecom.SubscriptionService.model.*;
import lombok.*;

@Getter
@Setter
public class UserDto
{
    private String name;
    private String email;
    private BigInteger contact;
    private String address;
    private Account account;
    private List<Subscription> subscriptionList = new ArrayList<>();
}