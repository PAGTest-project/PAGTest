
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Vehicle_standardSpecsTest {
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public String resolve(String key) {
                return "mock";
            }

            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // No-op implementation
            }
        };
        vehicle = new Vehicle(faker);
    }

    @Test
    public void testStandardSpecsWithMinMax() {
        int min = 5;
        int max = 10;
        List<String> specs = vehicle.standardSpecs(min, max);
        assertThat(specs).hasSizeBetween(min, max);
    }

    @Test
    public void testStandardSpecsWithMinEqualsMax() {
        int min = 7;
        int max = 7;
        List<String> specs = vehicle.standardSpecs(min, max);
        assertThat(specs).hasSize(min);
    }

    @Test
    public void testStandardSpecsWithMinGreaterThanMax() {
        int min = 10;
        int max = 5;
        List<String> specs = vehicle.standardSpecs(min, max);
        assertThat(specs).hasSizeBetween(max, min);
    }
}
