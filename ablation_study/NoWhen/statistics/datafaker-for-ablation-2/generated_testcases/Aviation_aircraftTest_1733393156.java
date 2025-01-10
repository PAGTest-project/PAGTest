
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Aviation_aircraftTest {

    @Test
    public void testAircraft() {
        BaseProviders faker = mock(BaseProviders.class);
        Number number = mock(Number.class);
        when(faker.number()).thenReturn(number);

        Aviation aviation = new Aviation(faker);

        // Mock the numberBetween method to return each index from 0 to 5
        for (int i = 0; i < 6; i++) {
            when(number.numberBetween(0, 6)).thenReturn(i);
            String result = aviation.aircraft();
            assertTrue(result.startsWith("aviation.aircraft."));
        }
    }
}
