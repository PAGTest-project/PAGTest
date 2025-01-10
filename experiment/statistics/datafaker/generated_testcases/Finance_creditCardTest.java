
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Finance_creditCardTest {

    private Finance finance;

    @BeforeEach
    public void setUp() {
        finance = new Finance(new BaseProviders() {});
    }

    @Test
    public void testCreditCardVisa() {
        String result = finance.creditCard(Finance.CreditCardType.VISA);
        assertTrue(result.matches("\\d{16}"));
        assertTrue(isValidLuhn(result));
    }

    @Test
    public void testCreditCardMastercard() {
        String result = finance.creditCard(Finance.CreditCardType.MASTERCARD);
        assertTrue(result.matches("\\d{16}"));
        assertTrue(isValidLuhn(result));
    }

    @Test
    public void testCreditCardAmericanExpress() {
        String result = finance.creditCard(Finance.CreditCardType.AMERICAN_EXPRESS);
        assertTrue(result.matches("\\d{15}"));
        assertTrue(isValidLuhn(result));
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
