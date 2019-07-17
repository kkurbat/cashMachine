package com.senla.cashMachine.entity;

import com.senla.cashMachine.dao.CardDao;
import com.senla.cashMachine.service.CardService;

//Controller class
public class CardMachine {
    private int balance=100000;
    private final int MAX_ATTEMPTS=3;
    private Card currentCard;
    private int attempts=0;
    private CardService cardService=new CardService();
    private CardDao cardDao=new CardDao();

    public CardMachine(String cardNumber) {
        this.currentCard = cardDao.getCardByCardNumber(cardNumber);
    }

    public int getMAX_ATTEMPTS() {
        return MAX_ATTEMPTS;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getBalance() {
        return balance;
    }

    public void depositMoney(int money){
        this.balance+=money;
    }

    public void withdrawMoney(int money){
        this.balance-=money;
    }

}
