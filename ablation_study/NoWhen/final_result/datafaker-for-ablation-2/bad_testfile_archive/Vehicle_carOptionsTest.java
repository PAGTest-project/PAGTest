
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Vehicle_carOptionsTest {

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
                // Dummy implementation to satisfy the abstract method
            }
        };
        vehicle = new Vehicle(faker);
    }

    @RepeatedTest(10)
    void testCarOptionsMinMax() {
        List<String> carOptions = vehicle.carOptions(5, 10);

        assertThat(carOptions)
            .hasSizeGreaterThanOrEqualTo(5)
            .hasSizeLessThanOrEqualTo(10);

        checkForDuplicates(carOptions);
    }

    private void checkForDuplicates(List<String> list) {
        assertThat(list).doesNotHaveDuplicates();
    }
}
