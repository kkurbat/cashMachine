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
            CashMachine cashMachine;

            while (true) {                                  ///Card number input
                consoleIO.printRequestforCardNumberOrExit();
                String requestedCard = scanner.nextLine();
                if (requestedCard.matches("0")) {
                    log.info("Cash machine was stopped");
                    return;
                }
                else if (cardNumberValidation.isCardNumberValide(requestedCard)) {
                    cashMachine=new CashMachine(requestedCard);
                    if(cashMachine.isCardFake()) consoleIO.printCardIsFake();
                    else if(cashMachine.getCurrentCard().isBlocked()) {
                        if(!cashMachine.tryToUnblockCard())
                            consoleIO.printBlockCard();
                    }
                    break;
                }
                else consoleIO.printNotValidCardNUmber();
            }

            boolean authorized=false;
            if(!(cashMachine.isCardFake() || cashMachine.getCurrentCard().isBlocked())) {
                do {                                       ///PIN input
                    MD5Encrypter md5 = new MD5Encrypter();
                    consoleIO.printRequestForPIN();
                    String requestedPin = scanner.nextLine();
                    try {
                        if (cashMachine.getCurrentCard().pinCodeEquals(md5.encrypt(requestedPin)))
                            authorized = true;
                        else {
                            cashMachine.attemptIncrease();
                            consoleIO.printPINIsIncorrect(3 - cashMachine.getAttempts());
                            if (cashMachine.getAttempts() == cashMachine.getMAX_ATTEMPTS()) {
                                consoleIO.printBlockCard();
                                cashMachine.blockCard();
                                saver.save(cashMachine);
                                break;
                            }
                        }
                    } catch (NoSuchAlgorithmException ex) { }
                }
                while (!authorized);
            }

            while(true && authorized){                      ///Menu
                consoleIO.printMenu();
                int request=consoleIO.integerInput();
                switch(request){
                    case 0:{
                        authorized=false;
                        break;
                    }
                    case 1:{
                        consoleIO.printBalance(cashMachine.getCardBalance());
                        break;
                    }
                    case 2:{
                        int money=consoleIO.requestForDeposit();
                        if(cashMachine.deposit2Card(money)) {
                            consoleIO.printSuccess();
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
                                consoleIO.printSuccess();
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
                saver.save(cashMachine);
            }

        }

    }
}
