package Telecom.SubscriptionService.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto
{
    private Integer price;
    private String planName;
    private String planDetails;
    private Long userId;
}