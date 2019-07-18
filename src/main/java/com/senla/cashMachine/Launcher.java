package com.senla.cashMachine;

import com.senla.cashMachine.entity.CashMachine;
import com.senla.cashMachine.exception.WithdrawException;
import com.senla.cashMachine.parser.DataSaver;
import com.senla.cashMachine.util.CardNumberValidation;
import com.senla.cashMachine.util.MD5Encrypter;
import com.senla.cashMachine.util.ConsoleIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Launcher {
    private static Logger log = LogManager.getLogger(Launcher.class.getName());

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        log.info("Cash machine is working");
        ConsoleIO consoleIO =new ConsoleIO();
        DataSaver saver=new DataSaver();
        CardNumberValidation cardNumberValidation=new CardNumberValidation();
        while(true) {
            String cardNumber;
            while (true) {///sigin
                consoleIO.printRequestforCardNumberOrExit();
                String requestedCard = scanner.nextLine();
                if (requestedCard.matches("0"))
                    return;
                else if (cardNumberValidation.isCardNumberValide(requestedCard)) {
                    cardNumber=new String(requestedCard);
                    break;
                }
                else
                    consoleIO.printNotValidCardNUmber();
            }

            CashMachine cashMachine=new CashMachine(cardNumber);//pin input
            boolean authorized=false;
            do{
                MD5Encrypter md5=new MD5Encrypter();
                consoleIO.printRequestForPIN();
                String requestedPin=scanner.nextLine();
                try {
                    if (cashMachine.getCurrentCard().pinCodeEquals(md5.encrypt(requestedPin)))
                        authorized = true;
                    else {
                        cashMachine.attemptIncrease();
                        if(cashMachine.getAttempts()>cashMachine.getMAX_ATTEMPTS()) {
                            consoleIO.printBlockCard();
                            cashMachine.blockCard();
                            break;
                        }
                    }
                }
                catch (NoSuchAlgorithmException ex){ }
            }
            while (!authorized);

            while(true && authorized){//menu
                consoleIO.printMenu();
                int request=consoleIO.integerInput();
                switch(request){
                    case 0:{
                        authorized=false;
                        break;
                    }
                    case 1:{
                        consoleIO.printBalance(cashMachine.getCurrentCard().getBalance());
                        break;
                    }
                    case 2:{
                        int money=consoleIO.requestForDeposit();
                        if(cashMachine.deposit2Card(money)) {
                            consoleIO.printSucces();
                            break;
                        }
                        else {
                            consoleIO.printFail("Input correct amount of money");
                            break;
                        }
                    }

                    case 3:{
                        int money=consoleIO.requestForWithdraw();
                        try{
                            if(cashMachine.withdrawFromCard(money)){
                                consoleIO.printSucces();
                                break;
                            }
                        }
                        catch (WithdrawException ex){
                            consoleIO.printFail(ex.getMessage());
                            break;
                        }
                    }
                    default:{
                        consoleIO.printFail("Choose again...");
                        break;
                    }
                }


            }
            saver.save(cashMachine.getCurrentCard());

        }

    }
}
