package Telecom.SubscriptionService.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import Telecom.SubscriptionService.dto.AccountDto;
import Telecom.SubscriptionService.dto.ResponseMessage;
import Telecom.SubscriptionService.model.Account;
import Telecom.SubscriptionService.model.User;
import Telecom.SubscriptionService.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService
{
    private final AccountRepository repository;
    private final UserService service;

    public List<Account> getAllAccounts()
    {
        return repository.findAll();
    }

    public Account getAccountById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account getAccountByUserId(Long userId)
    {
        return repository.findByUserId(userId);
    }

    @Transactional
    public Account updateAccount(Long id, AccountDto dto)
    {
        Account account = getAccountById(id);
        account.setBalance(dto.getBalance());
        account.setDetails(dto.getDetails());
        return account;
    }

    @Transactional
    public ResponseMessage createAccount(AccountDto dto)
    {
        Account account = new Account();
        account.setBalance(dto.getBalance());
        account.setDetails(dto.getDetails());
        User user = service.getUserById(dto.getUser().getId());
        user.setAccount(account);
        return new ResponseMessage("Account Created Successfully");
    }

    @Transactional
    public ResponseMessage deleteAccount(Long id)
    {
        Account account = getAccountById(id);
        repository.delete(account);
        return new ResponseMessage("Account Deleted Successfully");
    }
}