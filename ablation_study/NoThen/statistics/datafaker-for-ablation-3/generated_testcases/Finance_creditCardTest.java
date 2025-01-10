
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Finance_creditCardTest {

    @Test
    public void testCreditCard() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Finance finance = new Finance(faker);
        Finance.CreditCardType creditCardType = Finance.CreditCardType.VISA;
        String key = "finance.credit_card.visa";
        String value = "####-####-####-####";
        String template = "1234-5678-9012-3456";
        int[] digits = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6};
        int luhnSum = 68;
        int luhnDigit = 2;
        String expected = "1234-5678-9012-3452";

        when(finance.resolve(key)).thenReturn(value);
        when(faker.numerify(value)).thenReturn(template);

        // When
        String result = finance.creditCard(creditCardType);

        // Then
        assertEquals(expected, result);
    }
}
