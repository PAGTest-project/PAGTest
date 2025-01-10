
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Aviation_flightTest {

    private Aviation aviation;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation
            }

            @Override
            public FakeValuesService fakeValuesService() {
                return new FakeValuesService(new java.util.Locale("en"), new RandomService());
            }
        };
        aviation = new Aviation(faker);
    }

    @Test
    public void testFlightICAO() {
        String flightNumber = aviation.flight("ICAO");
        assertNotNull(flightNumber);
        assertTrue(flightNumber.matches("^[A-Z]{3}\\d{1,4}$"));
    }

    @Test
    public void testFlightIATA() {
        String flightNumber = aviation.flight("IATA");
        assertNotNull(flightNumber);
        assertTrue(flightNumber.matches("^[A-Z]{2}\\d{1,4}$"));
    }
}
