package Telecom.SubscriptionService.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import Telecom.SubscriptionService.client.BillingClient;
import Telecom.SubscriptionService.dto.InvoiceRequest;
import Telecom.SubscriptionService.dto.ResponseMessage;
import Telecom.SubscriptionService.dto.SubscriptionDto;
import Telecom.SubscriptionService.feign.BillingService;
import Telecom.SubscriptionService.model.Subscription;
import Telecom.SubscriptionService.model.User;
import Telecom.SubscriptionService.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionService
{
    private final SubscriptionRepository repository;
    private final UserService service;
    private final BillingService client;

    public List<Subscription> getAllSubscriptions()
    {
        return repository.findAll();
    }

    public Subscription getSubscriptionById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }

    public List<Subscription> getAllSubscriptionsByUser(Long id)
    {
        return repository.findAllByUserId(id);
    }

    @Transactional
    public ResponseMessage updateSubscription(Long id, SubscriptionDto dto)
    {
        Subscription subscription = getSubscriptionById(id);
        subscription.setPlanDetails(dto.getPlanDetails());
        subscription.setPlanName(dto.getPlanName());
        subscription.setPrice(dto.getPrice());
        repository.save(subscription);
        return new ResponseMessage("Subscription Updated Successfully");
    }

    @Transactional
    public ResponseMessage createSubscription(SubscriptionDto dto)
    {
        Subscription subscription = new Subscription();
        subscription.setPlanDetails(dto.getPlanDetails());
        subscription.setPlanName(dto.getPlanName());
        subscription.setPrice(dto.getPrice());
        User user = service.getUserById(dto.getUserId());
        subscription.setUser(user);
        user.getSubscriptionList().add(subscription);
        subscription = repository.save(subscription);
        InvoiceRequest invoiceRequest = new InvoiceRequest(subscription.getId(), user.getName(), subscription.getPrice());
        client.createInvoice(invoiceRequest);
        return new ResponseMessage("Subscription Created Successfully");
    }

    @Transactional
    public ResponseMessage deleteSubscription(Long id)
    {
        Subscription subscription = getSubscriptionById(id);
        subscription.getUser().getSubscriptionList().remove(subscription);
        repository.delete(subscription);
        return new ResponseMessage("Subscription Deleted Successfully");
    }
}