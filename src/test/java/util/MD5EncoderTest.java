package util;

import com.senla.cashMachine.util.MD5Encrypter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;

public class MD5EncoderTest {
    private final MD5Encrypter md5=new MD5Encrypter();
    private final String DECRYPTED_WORD="1111";
    private final String ENCRYPTED_WORD="b59c67bf196a4758191e42f76670ceba";

    @Test
    public void md5EncoderTest()throws NoSuchAlgorithmException {
        String result=md5.encrypt(DECRYPTED_WORD);
        Assert.assertEquals(result,ENCRYPTED_WORD);
    }
}
