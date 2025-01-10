
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Aviation_flightTest {
    private Aviation aviation;

    @BeforeEach
    public void setUp() {
        aviation = new Aviation(new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation of the abstract method
            }
        });
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

    @Test
    public void testFlightDefault() {
        String flightNumber = aviation.flight();
        assertNotNull(flightNumber);
        assertTrue(flightNumber.matches("^[A-Z]{2}\\d{1,4}$"));
    }
}
