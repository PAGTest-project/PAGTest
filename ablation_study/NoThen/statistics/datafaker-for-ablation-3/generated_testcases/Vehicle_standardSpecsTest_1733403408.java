
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Vehicle_standardSpecsTest {
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        vehicle = new Vehicle(faker);
    }

    @Test
    public void testStandardSpecsWithinRange() {
        int min = 5;
        int max = 10;
        List<String> specs = vehicle.standardSpecs(min, max);
        assertTrue(specs.size() >= min && specs.size() <= max);
    }

    @Test
    public void testStandardSpecsNotEmpty() {
        List<String> specs = vehicle.standardSpecs(5, 10);
        assertTrue(!specs.isEmpty());
    }

    @Test
    public void testStandardSpecsWithMinMax() {
        int min = 3;
        int max = 7;
        List<String> specs = vehicle.standardSpecs(min, max);
        assertTrue(specs.size() >= min && specs.size() <= max);
    }
}
