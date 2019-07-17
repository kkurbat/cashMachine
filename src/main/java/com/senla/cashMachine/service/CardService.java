package com.senla.cashMachine.service;

import com.senla.cashMachine.entity.Card;
import com.senla.cashMachine.entity.CardMachine;
import com.senla.cashMachine.entity.CardStatus;
import com.senla.cashMachine.exception.WithdrawException;
import com.senla.cashMachine.util.Coder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CardService {
    private static Logger log = LogManager.getLogger(Coder.class.getName());

    public double showBalance(Card card){
        return card.getBalance();
    }

    public void blockCard(Card card){
        log.info("Card : "+card.getCarNumber()+"was blocked");
        card.setCardStatus(CardStatus.BLOCKED);
    }

    public void unblockCard(Card card){
        log.info("Card : "+card.getCarNumber()+"was unblocked");
        card.setCardStatus(CardStatus.ACTIVE);
    }

    public boolean deposit(CardMachine cardMachine, int deposit){
        if(deposit>0 && deposit<1000000){
            cardMachine.getCurrentCard().depositMoney(deposit);
            cardMachine.depositMoney(deposit);
            log.info(deposit+" money was added to card :"+ cardMachine.getCurrentCard().getCarNumber());
            return true;
        }
        return false;
    }

    public boolean withdraw(CardMachine cardMachine,int withdraw)throws WithdrawException {
        if(withdraw>0 && withdraw<=cardMachine.getBalance() && withdraw<=cardMachine.getCurrentCard().getBalance()){
            cardMachine.withdrawMoney(withdraw);
            cardMachine.getCurrentCard().withdrawMoney(withdraw);
            log.info(withdraw+" money was withdrew from card :"+ cardMachine.getCurrentCard().getCarNumber());
            return true;
        }
        else if(withdraw>cardMachine.getBalance()){
            log.info("No enough money in cash machine");
            throw new WithdrawException("No enough money in cash machine");
        }
        else if(withdraw>cardMachine.getCurrentCard().getBalance()){
            log.error("No enough money in yours balance");
            throw new WithdrawException("No enough money in yours balance");
        }
        return false;
    }

}
