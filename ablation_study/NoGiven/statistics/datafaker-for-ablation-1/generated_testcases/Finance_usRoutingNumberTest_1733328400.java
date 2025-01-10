
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Finance_usRoutingNumberTest {

    @Test
    public void testUsRoutingNumber() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        when(faker.random().nextInt(12)).thenReturn(9); // Ensure random is 10 (09 in base)
        when(faker.numerify(anyString())).thenReturn("123456"); // Mock numerify to return "123456"
        Finance finance = new Finance(faker);

        // When
        String result = finance.usRoutingNumber();

        // Then
        assertEquals("091234564", result); // Expected result based on the mocked inputs
    }
}
