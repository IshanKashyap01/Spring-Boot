package Telecom.SubscriptionService.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Data
public class InvoiceRequest
{
    private Long subscriptionId;
    private String customerName;
    private LocalDate invoiceDate;
    private Integer amount;
    private List<Object> paymentList;

    public InvoiceRequest(Long subscriptionId, String customerName, Integer amount)
    {
        this.subscriptionId = subscriptionId;
        this.customerName = customerName;
        this.amount = amount;
        this.invoiceDate = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        this.paymentList = new ArrayList<>();
    }
}