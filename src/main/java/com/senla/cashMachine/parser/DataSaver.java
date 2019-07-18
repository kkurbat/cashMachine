package com.senla.cashMachine.parser;

import com.senla.cashMachine.entity.Card;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataSaver {

    public void save(Card changedCard){
        DataReader dataReader=new DataReader();
        DataParser dataParser=new DataParser();
        List<Card> cards=dataParser.parseData(dataReader.readData());
        try(FileWriter out = new FileWriter("src/main/resources/cards.txt")){
            for(int i=0;i<cards.size();i++){
                if(cards.get(i).getId()==changedCard.getId())
                    cards.set(i,changedCard);
                out.write(cards.get(i).toString() + System.lineSeparator());
            }
            out.flush();
        }
        catch (IOException ex){
        }
    }
}
