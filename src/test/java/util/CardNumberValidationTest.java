package util;

import com.senla.cashMachine.util.CardNumberValidation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CardNumberValidationTest {
    private CardNumberValidation validation=new CardNumberValidation();
    private final String CARD_NUMBER_1="1233-1233-1233-1233";
    private final String CARD_NUMBER_2="1233-1233-1233-12331";

    @Test
    public void CardNumberTest1(){
        Assert.assertTrue(validation.isCardNumberValide(CARD_NUMBER_1));
    }

    @Test
    public void CardNumberTest2(){
        Assert.assertFalse(validation.isCardNumberValide(CARD_NUMBER_2));
    }
}
