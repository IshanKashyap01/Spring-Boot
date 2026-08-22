package Telecom.SubscriptionService.client;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportClient
{
    private static final String SUPPORT_TICKET_URL = "http://support-service/api/ticket";
    private final RestTemplate template;

    public List<Object> getAllUserTickets(Long userId)
    {
        String resourceUri = SUPPORT_TICKET_URL + "/userId/" + userId;
        return template.exchange
        (
            resourceUri, HttpMethod.GET, null,
            new ParameterizedTypeReference<List<Object>>() {}
        )
        .getBody();
    }
}
