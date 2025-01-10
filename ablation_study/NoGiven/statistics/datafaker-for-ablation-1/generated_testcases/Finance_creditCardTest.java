
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Finance_creditCardTest {
    private Finance finance;

    @BeforeEach
    public void setUp() {
        finance = new Finance(new BaseProviders() {});
    }

    @Test
    public void testCreditCardVisa() {
        String result = finance.creditCard(Finance.CreditCardType.VISA);
        assertNotNull(result);
        assertTrue(result.matches("\\d{16}"));
        assertTrue(isValidLuhn(result));
    }

    @Test
    public void testCreditCardMastercard() {
        String result = finance.creditCard(Finance.CreditCardType.MASTERCARD);
        assertNotNull(result);
        assertTrue(result.matches("\\d{16}"));
        assertTrue(isValidLuhn(result));
    }

    @Test
    public void testCreditCardAmericanExpress() {
        String result = finance.creditCard(Finance.CreditCardType.AMERICAN_EXPRESS);
        assertNotNull(result);
        assertTrue(result.matches("\\d{15}"));
        assertTrue(isValidLuhn(result));
    }

    @Test
    public void testCreditCardDiscover() {
        String result = finance.creditCard(Finance.CreditCardType.DISCOVER);
        assertNotNull(result);
        assertTrue(result.matches("\\d{16}"));
        assertTrue(isValidLuhn(result));
    }

    @Test
    public void testCreditCardDinersClub() {
        String result = finance.creditCard(Finance.CreditCardType.DINERS_CLUB);
        assertNotNull(result);
        assertTrue(result.matches("\\d{14}"));
        assertTrue(isValidLuhn(result));
    }

    @Test
    public void testCreditCardJcb() {
        String result = finance.creditCard(Finance.CreditCardType.JCB);
        assertNotNull(result);
        assertTrue(result.matches("\\d{16}"));
        assertTrue(isValidLuhn(result));
    }

    @Test
    public void testCreditCardSwitch() {
        String result = finance.creditCard(Finance.CreditCardType.SWITCH);
        assertNotNull(result);
        assertTrue(result.matches("\\d{16}"));
        assertTrue(isValidLuhn(result));
    }

    @Test
    public void testCreditCardSolo() {
        String result = finance.creditCard(Finance.CreditCardType.SOLO);
        assertNotNull(result);
        assertTrue(result.matches("\\d{16}"));
        assertTrue(isValidLuhn(result));
    }

    @Test
    public void testCreditCardDankort() {
        String result = finance.creditCard(Finance.CreditCardType.DANKORT);
        assertNotNull(result);
        assertTrue(result.matches("\\d{16}"));
        assertTrue(isValidLuhn(result));
    }

    @Test
    public void testCreditCardForbrugsforeningen() {
        String result = finance.creditCard(Finance.CreditCardType.FORBRUGSFORENINGEN);
        assertNotNull(result);
        assertTrue(result.matches("\\d{16}"));
        assertTrue(isValidLuhn(result));
    }

    @Test
    public void testCreditCardLaser() {
        String result = finance.creditCard(Finance.CreditCardType.LASER);
        assertNotNull(result);
        assertTrue(result.matches("\\d{16}"));
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
