package com.senla.cashMachine.parser;

import com.senla.cashMachine.entity.Card;
import com.senla.cashMachine.entity.CardStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataParser {

    public List<Card> parseData(List<String> lines){
        List<Card> cards=new ArrayList<Card>();
        for(String line: lines){
            String[] data=line.split(" ");
            Card card=new Card(Integer.parseInt(data[0]),data[1],Integer.parseInt(data[2]),data[3],CardStatus.valueOf(data[4].toUpperCase()),getData(data[5]));
            cards.add(card);
        }
        return cards;
    }

    private LocalDate getData(String date){
        LocalDate localDate=LocalDate.MIN;
        try {
           localDate = LocalDate.parse(date);
        }
        catch (Exception ex){
            return null;
        }
        return localDate;

    }


}
