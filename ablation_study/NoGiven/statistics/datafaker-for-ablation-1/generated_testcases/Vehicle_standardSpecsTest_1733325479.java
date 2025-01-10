
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Vehicle_standardSpecsTest {

    private Vehicle vehicle;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
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
