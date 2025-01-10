
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class Aviation_aircraftTest {

    private Aviation aviation;
    private BaseProviders faker;
    private Number number;

    @BeforeEach
    public void setup() {
        faker = Mockito.mock(BaseProviders.class);
        number = Mockito.mock(Number.class);
        when(faker.number()).thenReturn(number);
        aviation = new Aviation(faker);
    }

    @Test
    public void testAircraft() {
        List<String> aircraftTypes = List.of("aviation.aircraft.airplane", "aviation.aircraft.warplane",
                "aviation.aircraft.army_helicopter", "aviation.aircraft.civil_helicopter",
                "aviation.aircraft.general", "aviation.aircraft.cargo");

        for (int i = 0; i < aircraftTypes.size(); i++) {
            when(number.numberBetween(0, 6)).thenReturn(i);
            String result = aviation.aircraft();
            assertTrue(aircraftTypes.contains(result));
        }
    }
}
