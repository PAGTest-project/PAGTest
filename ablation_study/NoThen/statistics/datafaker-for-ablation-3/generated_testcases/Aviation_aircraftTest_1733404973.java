
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Aviation_aircraftTest {
    private Aviation aviation;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        aviation = new Aviation(faker);
    }

    @Test
    void aircraft_shouldReturnOneOfSixTypes() {
        String result = aviation.aircraft();
        assertThat(result).isIn(
            "aviation.aircraft.airplane",
            "aviation.aircraft.warplane",
            "aviation.aircraft.army_helicopter",
            "aviation.aircraft.civil_helicopter",
            "aviation.aircraft.general",
            "aviation.aircraft.cargo"
        );
    }

    @Test
    void airplane_shouldReturnAirplaneType() {
        assertThat(aviation.airplane()).isEqualTo("aviation.aircraft.airplane");
    }

    @Test
    void warplane_shouldReturnWarplaneType() {
        assertThat(aviation.warplane()).isEqualTo("aviation.aircraft.warplane");
    }

    @Test
    void general_shouldReturnGeneralType() {
        assertThat(aviation.general()).isEqualTo("aviation.aircraft.general");
    }

    @Test
    void cargo_shouldReturnCargoType() {
        assertThat(aviation.cargo()).isEqualTo("aviation.aircraft.cargo");
    }

    @Test
    void armyHelicopter_shouldReturnArmyHelicopterType() {
        assertThat(aviation.armyHelicopter()).isEqualTo("aviation.aircraft.army_helicopter");
    }

    @Test
    void civilHelicopter_shouldReturnCivilHelicopterType() {
        assertThat(aviation.civilHelicopter()).isEqualTo("aviation.aircraft.civil_helicopter");
    }

    @Test
    void flight_default_shouldMatchPattern() {
        assertThat(aviation.flight()).matches("[A-Z0-9]{2}\\d{1,4}");
    }
}
