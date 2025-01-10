
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Finance_creditCardTest {
    private Finance finance;

    @BeforeEach
    public void setUp() {
        finance = new Finance(new BaseFaker());
    }

    @Test
    void testCreditCardVisa() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.VISA);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    void testCreditCardMastercard() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.MASTERCARD);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    void testCreditCardInvalidType() {
        String creditCardNumber = finance.creditCard(null);
        assertFalse(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    void testCreditCardAmericanExpress() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.AMERICAN_EXPRESS);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    void testCreditCardDiscover() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.DISCOVER);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }
}
