package com.senla.cashMachine.parser;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    private final String FILE_PATH="src/main/resources/data/cards.txt";

    public List<String> readData(){
        List<String> lines=new ArrayList<String>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line= in.readLine())!=null)
                lines.add(line);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return  lines;
    }

}
