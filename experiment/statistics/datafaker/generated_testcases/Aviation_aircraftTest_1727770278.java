
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class Aviation_aircraftTest {
    private Aviation aviation;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        aviation = new Aviation(faker);
    }

    @Test
    void testAircraft() {
        List<String> expectedTypes = List.of(
            "aviation.aircraft.airplane",
            "aviation.aircraft.warplane",
            "aviation.aircraft.army_helicopter",
            "aviation.aircraft.civil_helicopter",
            "aviation.aircraft.general",
            "aviation.aircraft.cargo"
        );

        String result = aviation.aircraft();
        assertThat(expectedTypes).contains(result);
    }
}
