package com.security.bank.service;

import com.security.bank.entity.*;
import com.security.bank.dto.CardDto;
import lombok.RequiredArgsConstructor;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.security.bank.exception.CardNotFoundException;
import com.security.bank.repository.AccountRepository;
import com.security.bank.repository.CardRepository;
import com.security.bank.util.CardGenerator;

@Service
@RequiredArgsConstructor
public class CardService
{
    private final CardGenerator generator;
    private final AccountService service;
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    public void blockCard(Long accountNumber, Long cardNumber)
    {
        Account account = service.getAccountByAccountNumber(accountNumber);
        Card card = cardRepository.findByCardNumber(cardNumber).get();
        if(!account.getCard().getCardNumber().equals(card.getCardNumber()))
        {
            throw new CardNotFoundException("No Card found with the given cardNumber: " + cardNumber);
        }
        account.setCard(null);
        cardRepository.deleteById(card.getId());
        accountRepository.save(account);
    }

    public String applyForCard(Long accountNumber, CardDto dto)
    {
        Account account = accountRepository.findByAccountNumber(accountNumber).get();
        if(account.getCard() != null && account.getCard().getCardNumber() != null){
            throw new RuntimeException("Account with number: "+ accountNumber+" already has a card.");
        }
        Card card = new Card();
        card.setCardNumber(Long.parseLong(generator.generateCardNumber()));
        card.setCvv(generator.generateCvv());
        switch (dto.getCardType()) {
            case "DEBIT_CLASSIC" -> {
                card.setDailyLimit(20000);
                card.setCardType(CardType.DEBIT_CLASSIC);
            }
            case "CREDIT_PREMIUM" -> {
                card.setDailyLimit(50000);
                card.setCardType(CardType.CREDIT_PREMIUM);
            }
            case "CREDIT_MASTER" -> {
                card.setDailyLimit(75000);
                card.setCardType(CardType.CREDIT_MASTER);
            }
            default -> {
                card.setDailyLimit(40000);
                card.setCardType(CardType.DEBIT_GLOBAL);
            }
        }
        card.setPin(dto.getPin());
        card.setAllocationDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 5);
        card.setExpiryDate(calendar.getTime());
        card.setCardHolderName(dto.getCardHolderName());
        card.setStatus("ACTIVE");
        Card savedCard = cardRepository.save(card);
        account.setCard(savedCard);
        accountRepository.save(account);
        return "New Card Allocated to account wih Number: "+ accountNumber;
    }

    public void updateCardLimitAndPin(Card carDto, Long cardNumber)
    {
        Optional<Card> optionalCard = cardRepository.findByCardNumber(cardNumber);
        Double cardLimit = carDto.getDailyLimit();
        Card fetchedCard = optionalCard.get();
        switch(fetchedCard.getCardType()){
            case DEBIT_GLOBAL -> {
            if(cardLimit != 0 && carDto.getDailyLimit()<= 50000) fetchedCard.setDailyLimit(carDto.getDailyLimit());
            }
            case DEBIT_CLASSIC -> {
            if(cardLimit != 0 && carDto.getDailyLimit()<= 40000) fetchedCard.setDailyLimit(carDto.getDailyLimit());
            }
            case CREDIT_MASTER -> {
            if(cardLimit != 0 && carDto.getDailyLimit()<= 100000) fetchedCard.setDailyLimit(carDto.getDailyLimit());
            }
            case CREDIT_PREMIUM -> {
            if(cardLimit != 0 && carDto.getDailyLimit()<= 75000)  fetchedCard.setDailyLimit(carDto.getDailyLimit());
            }
        }
        if(carDto.getPin()!=null) fetchedCard.setPin(carDto.getPin());
        cardRepository.save(fetchedCard);
    }
}