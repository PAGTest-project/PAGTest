
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
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        when(faker.number()).thenReturn(number);
        when(faker.fakeValuesService()).thenReturn(fakeValuesService);

        Aviation aviation = new Aviation(faker);

        // Mock the numberBetween method to return each index from 0 to 5
        for (int i = 0; i < 6; i++) {
            when(number.numberBetween(0, 6)).thenReturn(i);
            when(fakeValuesService.resolve("aviation.aircraft.airplane", aviation, null)).thenReturn("aviation.aircraft.airplane");
            when(fakeValuesService.resolve("aviation.aircraft.warplane", aviation, null)).thenReturn("aviation.aircraft.warplane");
            when(fakeValuesService.resolve("aviation.aircraft.army_helicopter", aviation, null)).thenReturn("aviation.aircraft.army_helicopter");
            when(fakeValuesService.resolve("aviation.aircraft.civil_helicopter", aviation, null)).thenReturn("aviation.aircraft.civil_helicopter");
            when(fakeValuesService.resolve("aviation.aircraft.general", aviation, null)).thenReturn("aviation.aircraft.general");
            when(fakeValuesService.resolve("aviation.aircraft.cargo", aviation, null)).thenReturn("aviation.aircraft.cargo");

            String result = aviation.aircraft();
            assertTrue(result.startsWith("aviation.aircraft."));
        }
    }
}
