
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Finance_creditCardTest {

    private Finance finance;

    @BeforeEach
    public void setUp() {
        finance = new Finance(new BaseProviders());
    }

    @Test
    public void testCreditCardVisa() {
        String result = finance.creditCard(Finance.CreditCardType.VISA);
        assertTrue(result.matches("\\d{16}"));
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(result));
    }

    @Test
    public void testCreditCardMastercard() {
        String result = finance.creditCard(Finance.CreditCardType.MASTERCARD);
        assertTrue(result.matches("\\d{16}"));
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(result));
    }

    @Test
    public void testCreditCardAmericanExpress() {
        String result = finance.creditCard(Finance.CreditCardType.AMERICAN_EXPRESS);
        assertTrue(result.matches("\\d{15}"));
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(result));
    }
}
