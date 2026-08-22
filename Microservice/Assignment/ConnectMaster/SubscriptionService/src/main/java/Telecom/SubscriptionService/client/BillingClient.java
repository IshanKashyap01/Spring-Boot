package Telecom.SubscriptionService.client;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import Telecom.SubscriptionService.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillingClient
{
    private static final String BILLING_INVOICE_URL = "http://billing-client/invoice";
    private final RestTemplate template;

    public ResponseMessage createInvoice(Long subscriptionId, String name, Integer amount)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("subscriptionId", subscriptionId);
        map.put("customerName", name);
        // will convert to yyyy-mm-dd of today's date in Indian Standard Time zone
        // upon serialization
        map.put("invoiceDate", LocalDate.now(ZoneId.of("Asia/Kolkata")));
        map.put("amount", amount);
        map.put("paymentList", new ArrayList<>());
        return template.postForObject(BILLING_INVOICE_URL, map, ResponseMessage.class);
    }
}