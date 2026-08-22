package Telecom.SubscriptionService.dto;

import Telecom.SubscriptionService.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto
{
    private User user;
    private String balance;
    private String details;
}