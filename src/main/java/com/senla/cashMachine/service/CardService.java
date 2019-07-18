package com.senla.cashMachine.service;

import com.senla.cashMachine.entity.Card;
import com.senla.cashMachine.entity.CashMachine;
import com.senla.cashMachine.entity.CardStatus;
import com.senla.cashMachine.exception.WithdrawException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class CardService {
    private static Logger log = LogManager.getLogger(CardService.class.getName());

    public int showBalance(Card card){
        return card.getBalance();
    }

    public void blockCard(Card card){
        log.info("Card : "+card.getCarNumber()+"was blocked");
        card.setCardStatus(CardStatus.BLOCKED);
        card.setLastBlockDate(LocalDate.now());
    }

    public void unblockCard(Card card){
        log.info("Card : "+card.getCarNumber()+"was unblocked");
        card.setCardStatus(CardStatus.ACTIVE);
    }

    public boolean deposit(CashMachine cashMachine, int deposit){
        if(deposit>0 && deposit<1000000){
            cashMachine.getCurrentCard().depositMoney(deposit);
            cashMachine.depositMoney(deposit);
            log.info(deposit+" money was added to card :"+ cashMachine.getCurrentCard().getCarNumber());
            return true;
        }
        return false;
    }

    public boolean withdraw(CashMachine cashMachine, int withdraw)throws WithdrawException {
        if(withdraw>0 && withdraw<= cashMachine.getBalance() && withdraw<= cashMachine.getCurrentCard().getBalance()){
            cashMachine.withdrawMoney(withdraw);
            cashMachine.getCurrentCard().withdrawMoney(withdraw);
            log.info(withdraw+" money was withdrew from card :"+ cashMachine.getCurrentCard().getCarNumber());
            return true;
        }
        else if(withdraw> cashMachine.getBalance()){
            log.info("No enough money in cash machine");
            throw new WithdrawException("No enough money in cash machine");
        }
        else if(withdraw> cashMachine.getCurrentCard().getBalance()){
            log.error("No enough money in yours balance");
            throw new WithdrawException("No enough money in yours balance");
        }
        return false;
    }

}
