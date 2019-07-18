package com.senla.cashMachine.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleIO {

    public void printRequestforCardNumberOrExit(){
        System.out.println("Input card number or print 0 to close the program : ");
    }

    public void printRequestForPIN(){
        System.out.println("Input pin :");
    }

    public void printNotValidCardNUmber(){
        System.out.println("Your's card number is not valid,card number,card number must match the format XXXX-XXXX-XXXX-XXXX");
    }

    public void printCardNumberDontExist(){
        System.out.println("This card number don't exist,try another one");
    }

    public void printPINIsIncorrect(int atempts){
        System.out.println("PIN IS INCORRECT");
        System.out.println(atempts+" are(is) left to input PIN,or your's card will be blocked ");
        printRequestForPIN();
    }

    public void printMenu(){
        System.out.println("Choose operation:");
        System.out.println("1.Show balance");
        System.out.println("2.Deposit money");
        System.out.println("3.Withdraw money");
        System.out.println("0.Logout");
    }

    public void printBalance(int balance){
        System.out.println("You're balance is "+balance);
    }

    public int requestForDeposit(){
        System.out.println("Input amount to deposit : ");
        return integerInput();
    }

    public int requestForWithdraw(){
        System.out.println("Input amount to withdraw : ");
        return integerInput();
    }

    public void printBlockCard(){
        System.out.println("Your's card is blocked,card will be unblocked tomorrow");
        System.out.println("Try another card..");
    }

    public void printSucces(){
        System.out.println("Operation was ended successfully");
    }

    public void printFail(String message){
        System.out.println("FAIL");
        System.out.println(message);
    }

    public int integerInput(){
        Scanner scan = new Scanner(System.in);
        try {
            return scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Number must be integer!");
        }
        return -1;
    }



}
