package com.senla.cashMachine.util;

public class CardNumberValidation {
    private final String CARD_NUMBER_PATTERN="^\\d{4}(-\\d{4}){3}$";

    public  boolean isCardNumberValide(String cardNumber){
        return cardNumber.matches(CARD_NUMBER_PATTERN);
    }
}
