package Telecom.SubscriptionService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import Telecom.SubscriptionService.dto.InvoiceRequest;
import Telecom.SubscriptionService.dto.ResponseMessage;

@FeignClient(name = "billing-service")
public interface BillingService
{
    @PostMapping("/invoice")
    public ResponseMessage createInvoice(InvoiceRequest request);
}