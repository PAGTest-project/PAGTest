
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class Aviation_aircraftTest {
    private Aviation aviation;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation
            }

            @Override
            public <T extends AbstractProvider> T getProvider(Class<T> clazz, java.util.function.Function<BaseProviders, T> creator) {
                return creator.apply(this);
            }
        };
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
