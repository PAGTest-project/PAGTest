
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Vehicle_standardSpecsTest {
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public String resolve(String key) {
                return "mockValue";
            }

            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation not needed for this test
            }
        };
        vehicle = new Vehicle(faker);
    }

    @RepeatedTest(10)
    void testStandardSpecsMinMax() {
        List<String> standardSpecs = vehicle.standardSpecs(11, 12);

        assertThat(standardSpecs)
            .hasSizeGreaterThanOrEqualTo(11)
            .hasSizeLessThanOrEqualTo(12);

        assertThat(standardSpecs.get(0)).isNotNull();
    }
}
