package com.senla.cashMachine.dao;

import com.senla.cashMachine.entity.Card;
import com.senla.cashMachine.parser.DataParser;
import com.senla.cashMachine.parser.DataReader;

import java.util.List;

public class CardDao {
    private List<Card> cards;

    public CardDao(){
        DataReader dataReader=new DataReader();
        DataParser dataParser=new DataParser();
        cards=dataParser.parseData(dataReader.readData());
    }

    public Card getCardByCardNumber(String cardNumber){
        for(Card card:cards){
            if(card.getCarNumber().equals(cardNumber))
                return card;
        }
        return null;
    }
}
