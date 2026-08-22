package com.security.bank.util;

import java.util.*;
import com.security.bank.entity.*;
import java.security.SecureRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.security.bank.repository.CardRepository;

@Component
@RequiredArgsConstructor
public class CardGenerator
{
    private final CardRepository repository;
    /**
     * Generates a card with all details except pin
     * @param cardType type of card to generate
     * @return fully initialised card with no pin
     */
    public Card generateCard(String cardHolderName, String cardType)
    {
        Card card = new Card();
        setUniqueCardNumber(card);
        card.setCardHolderName(cardHolderName);
        setCardTypeAndDailyLimit(card, cardType);
        card.setCvv(new SecureRandom().nextInt(100, 999));
        setAllocationAndExpiryDate(card);
        card.setStatus("ACTIVE");
        return card;
    }

    public void setUniqueCardNumber(Card card)
    {
        do
        {
            card.setCardNumber(generate16DigitsRandomNumber());
        }while(repository.findByCardNumber(card.getCardNumber()).isPresent());
    }

    public Long generate16DigitsRandomNumber()
    {
        // SecureRandom secureRandom = new SecureRandom();
        // long firstDigit = 1L + secureRandom.nextInt(9); 
        // long remainingDigits = Math.abs(secureRandom.nextLong() % 1_000_000_000_000_000L); 
        // return (firstDigit * 1_000_000_000_000_000L) + remainingDigits;
        return 2233446127128L;
    }

    public void setCardTypeAndDailyLimit(Card card, String cardType)
    {
        if(cardType.equalsIgnoreCase("debit_classic"))
        {
            card.setCardTypeAndDailyLimit(CardType.DEBIT_CLASSIC, 40_000);
        }
        else if(cardType.equalsIgnoreCase("credit_premium"))
        {
            card.setCardTypeAndDailyLimit(CardType.CREDIT_PREMIUM, 50_000);
        }
        else if(cardType.equalsIgnoreCase("credit_master"))
        {
            card.setCardTypeAndDailyLimit(CardType.CREDIT_MASTER, 75_000);
        }
        else if(cardType.equalsIgnoreCase("debit_global"))
        {
            card.setCardTypeAndDailyLimit(CardType.DEBIT_GLOBAL, 40_000);
        }
    }

    private void setAllocationAndExpiryDate(Card card)
    {
        card.setAllocationDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 5);
        card.setExpiryDate(calendar.getTime());
    }

    public Long generateRandomNumber() {
	        Random random = new Random();
	        // Generate a random number between 10000000 and 99999999 (8-digit number)
	        return 10000000 + random.nextLong(90000000);
	    }
	  
    public int generateCvv() {
        Random random = new Random();
        // Generating a random 3-digit number for CVV
        return random.nextInt(900) + 100;
    }

    public String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        Random random = new Random();

        // Generating a 16-digit card number
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);
        }
        return cardNumber.toString();
	    }
}