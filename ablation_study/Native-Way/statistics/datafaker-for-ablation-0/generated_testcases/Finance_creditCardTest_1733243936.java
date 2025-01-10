
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
        assertTrue(isValidLuhn(creditCardNumber));
    }

    @Test
    void testCreditCardMastercard() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.MASTERCARD);
        assertTrue(isValidLuhn(creditCardNumber));
    }

    @Test
    void testCreditCardInvalidType() {
        String creditCardNumber = finance.creditCard(null);
        assertFalse(isValidLuhn(creditCardNumber));
    }

    @Test
    void testCreditCardAmericanExpress() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.AMERICAN_EXPRESS);
        assertTrue(isValidLuhn(creditCardNumber));
    }

    @Test
    void testCreditCardDiscover() {
        String creditCardNumber = finance.creditCard(Finance.CreditCardType.DISCOVER);
        assertTrue(isValidLuhn(creditCardNumber));
    }

    private boolean isValidLuhn(String number) {
        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(number.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
