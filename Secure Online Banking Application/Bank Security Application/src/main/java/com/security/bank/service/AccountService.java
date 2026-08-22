package com.security.bank.service;

import java.util.*;
import com.security.bank.dto.*;
import com.security.bank.entity.*;
import lombok.RequiredArgsConstructor;
import com.security.bank.util.CardGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.security.bank.repository.AccountRepository;
import com.security.bank.repository.CardRepository;
import com.security.bank.repository.NomineeRepository;
import com.security.bank.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AccountService
{
    private final AccountRepository repository;
    private final CardGenerator generator;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    CardRepository cardRepository;
    
    @Autowired
    NomineeRepository nomineeRepository;

    public void createAccountForUser(AccountDto dto, Long userId)
    {
        User user = new User();
	       if(userRepository.existsById(userId)){
	           user = userRepository.findById(userId).get();
	       }
	       Account account = new Account();
	       Card card = new Card();
	       card.setCardNumber(Long.parseLong(generator.generateCardNumber()));
	       card.setCvv(generator.generateCvv());
	       card.setCardHolderName(user.getName());
	       card.setStatus("ACTIVE");
	       card.setPin(1122L);
	       card.setAllocationDate(new Date());
	       Calendar calendar = Calendar.getInstance();
	       calendar.setTime(new Date());
	       calendar.add(Calendar.YEAR, 5);
	       card.setExpiryDate(calendar.getTime());

	       switch(dto.getAccountType()){
	           case "SAVINGS":{
	               card.setCardType(CardType.DEBIT_GLOBAL);
	               card.setDailyLimit(40000);
	               cardRepository.save(card);
	               account.setAccountType(AccountType.SAVINGS);
	               account.setInterestRate(2.70F);
	               account.setBranch(BranchType.BOB);
	               account.setCard(card);
	               break;
	           }
	           case "CURRENT":{
	               card.setCardType(CardType.CREDIT_PREMIUM);
	               card.setDailyLimit(50000);
	               cardRepository.save(card);
	               account.setAccountType(AccountType.CURRENT);
	               account.setBranch(BranchType.ICIC);
	               account.setCard(card);
	               account.setInterestRate(5.2F);
	               break;
	           }
	           case "PPF":{
	               account.setAccountType(AccountType.PPF);
	               account.setBranch(BranchType.SBI);
	               account.setInterestRate(7.4F);
	               break;
	           }
	           case "SALARY":{
	               card.setCardType(CardType.CREDIT_MASTER);
	               card.setDailyLimit(75000);
	               cardRepository.save(card);
	               account.setAccountType(AccountType.SALARY);
	               account.setBranch(BranchType.HDFC);
	               account.setCard(card);
	               account.setInterestRate(4.1F);
	               break;
	           }
	           default:{
	               throw  new RuntimeException("No AccountType Selected");
	           }
	       }
	       account.setAccountNumber(generator.generateRandomNumber());
	       account.setStatus("ACTIVE");
	       account.setBalance(dto.getBalance());
	       Nominee nominee = nomineeRepository.save(dto.getNominee());
	       account.setNominee(nominee);
	       account.setProof(dto.getProof());
	       account.setOpeningDate(new Date());
	       account.setUser(user);
	       accountRepository.save(account);
    }

    public List<Account> getAllUserAccounts(Long userId)
    {
        return repository.findByUser_Id(userId);
    }

    public Account getAccountById(Long accountId)
    {
        return repository.findById(accountId).get();
    }

    public double getAccountBalance(Long accountNumber)
    {
        return repository.getBalance(accountNumber);
    }

    public Nominee getAccountNominee(Long accountNumber)
    {
        return repository.findNomineeByAccountNumber(accountNumber);
    }

    public void updateNominee(NomineeDto dto, Long id)
    {
        Account account = getAccountById(id);
        Nominee nominee = account.getNominee();
        nominee.setAccountNumber(dto.getAccountNumber());
        nominee.setAge(dto.getAge());
        nominee.setGender(dto.getGender());
        nominee.setName(dto.getName());
        nominee.setRelation(dto.getRelation());
        accountRepository.save(account);
    }

    public User getAccountUser(Long accountNumber)
    {
        return repository.findUserByAccountNumber(accountNumber);
    }

    public User updateKyc(KycDto dto, Long accountId)
    {
        Account account = getAccountById(accountId);
        User user = account.getUser();
        if(dto.getName() != null)
        {
            user.setName(dto.getName());
        }
        if(dto.getAddress() != null)
        {
            user.setAddress(dto.getAddress());
        }
        if(dto.getNumber() != null)
        {
            user.setNumber(dto.getNumber());
        }
        if(dto.getIdentityProof() != null)
        {
            user.setIdentityProof(dto.getIdentityProof());
        }
        userRepository.save(user);
        return user;
    }

    public Account getAccountByAccountNumber(Long accountNumber)
    {
        Account account = repository.findByAccountNumber(accountNumber).get();
        account.setUser(null);
        return account;
    }
}