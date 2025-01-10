
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Aviation_flightTest {
    private Aviation aviation;

    @BeforeEach
    public void setUp() {
        aviation = new Aviation(new BaseProviders() {});
    }

    @Test
    void testFlightICAO() {
        String flightNumber = aviation.flight("ICAO");
        assertThat(flightNumber).isNotEmpty();
        assertThat(flightNumber).matches("^[A-Z]{3}\\d{1,4}$");
    }

    @Test
    void testFlightIATA() {
        String flightNumber = aviation.flight("IATA");
        assertThat(flightNumber).isNotEmpty();
        assertThat(flightNumber).matches("^[A-Z]{2}\\d{1,4}$");
    }

    @Test
    void testFlightDefault() {
        String flightNumber = aviation.flight();
        assertThat(flightNumber).isNotEmpty();
        assertThat(flightNumber).matches("^[A-Z]{2}\\d{1,4}$");
    }

    @Test
    void testFlightStatus() {
        String flightStatus = aviation.flightStatus();
        assertThat(flightStatus).isNotEmpty();
    }

    @Test
    void testGate() {
        String gate = aviation.gate();
        assertThat(gate).isNotEmpty();
    }

    @Test
    void testAirline() {
        String airline = aviation.airline();
        assertThat(airline).isNotEmpty();
    }

    @Test
    void testAirport() {
        String airport = aviation.airport();
        assertThat(airport).isNotEmpty();
    }
}
