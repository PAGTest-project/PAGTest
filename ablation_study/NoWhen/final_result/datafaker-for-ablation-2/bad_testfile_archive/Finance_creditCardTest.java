
package net.datafaker.providers.base;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Finance_creditCardTest {
    private Finance finance;

    @BeforeEach
    public void setUp() {
        finance = new Finance(new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation to satisfy the abstract method
            }
        });
    }

    @Test
    public void testCreditCardVisa() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.VISA);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    public void testCreditCardMastercard() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.MASTERCARD);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    public void testCreditCardDiscover() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.DISCOVER);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    public void testCreditCardAmericanExpress() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.AMERICAN_EXPRESS);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    public void testCreditCardDinersClub() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.DINERS_CLUB);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    public void testCreditCardJcb() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.JCB);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    public void testCreditCardSwitch() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.SWITCH);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    public void testCreditCardSolo() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.SOLO);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    public void testCreditCardDankort() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.DANKORT);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    public void testCreditCardForbrugsforeningen() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.FORBRUGSFORENINGEN);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }

    @Test
    public void testCreditCardLaser() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.LASER);
        assertTrue(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber));
    }
}
