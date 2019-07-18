package com.senla.cashMachine.entity;

import java.time.LocalDate;

public class Card {

    private int id;
    private String cardNumber;
    private int balance;
    private String pinCode;
    private CardStatus cardStatus;
    private LocalDate lastBlockDate;

    public Card(int id, String cardNumber, int balance, String pinCode, CardStatus cardStatus, LocalDate lastBlockDate) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.pinCode = pinCode;
        this.cardStatus = cardStatus;
        this.lastBlockDate = lastBlockDate;
    }

    public String getCarNumber() {
        return cardNumber;
    }


    public int getBalance() {
        return balance;
    }

    public String getPinCode() {
        return pinCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public LocalDate getLastBlockDate() {
        return lastBlockDate;
    }

    public void setLastBlockDate(LocalDate lastBlockDate) {
        this.lastBlockDate = lastBlockDate;
    }

    public void depositMoney(int money){
        this.balance+=money;
    }

    public void withdrawMoney(int money){
        this.balance-=money;
    }

    public boolean pinCodeEquals(String pinCode){
        return (this.pinCode.equals(pinCode))? true:false;
    }

    @Override
    public String toString() {
        return id+" "+cardNumber+" "+balance+" "+pinCode+" "+cardStatus+" "+lastBlockDate;
    }
}
