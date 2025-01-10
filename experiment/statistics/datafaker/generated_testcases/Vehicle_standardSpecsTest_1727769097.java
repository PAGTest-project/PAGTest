
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Vehicle_standardSpecsTest {

    private Vehicle vehicle;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseProviders();
        vehicle = new Vehicle(faker);
    }

    @Test
    void testStandardSpecsMinMax() {
        List<String> standardSpecs = vehicle.standardSpecs(5, 10);

        assertThat(standardSpecs)
            .hasSizeGreaterThanOrEqualTo(5)
            .hasSizeLessThanOrEqualTo(10);

        assertThat(standardSpecs.get(0)).isNotNull();
    }
}
