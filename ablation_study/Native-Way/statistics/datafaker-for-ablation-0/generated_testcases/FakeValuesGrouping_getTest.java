
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeValuesGrouping_getTest {
    private FakeValuesGrouping fakeValuesGrouping;
    private FakeValues addressValues;

    @BeforeEach
    void setUp() {
        fakeValuesGrouping = new FakeValuesGrouping();
        addressValues = FakeValues.of(FakeValuesContext.of(Locale.ENGLISH, "address.yml", "address"));
        fakeValuesGrouping.add(addressValues);
    }

    @Test
    void testGetWithExistingKey() {
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("street", "123 Main St");
        expectedResult.put("city", "Anytown");
        expectedResult.put("zipcode", "12345");

        addressValues.getPaths().forEach(path -> {
            Map<String, Object> fakeValueMap = new HashMap<>();
            fakeValueMap.put(path, "value");
            fakeValuesGrouping.add(new FakeValues(FakeValuesContext.of(Locale.ENGLISH, "address.yml", "address")) {
                @Override
                public Map<String, Object> get(String key) {
                    return fakeValueMap;
                }

                @Override
                public java.util.Collection<String> getPaths() {
                    return Collections.singleton(path);
                }
            });
        });

        Map<String, String> result = fakeValuesGrouping.get("address");
        assertThat(result).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void testGetWithNonExistingKey() {
        Map<String, String> result = fakeValuesGrouping.get("nonexistent");
        assertThat(result).isNull();
    }

    @Test
    void testGetWithEmptyFakeValues() {
        FakeValuesGrouping emptyGrouping = new FakeValuesGrouping();
        Map<String, String> result = emptyGrouping.get("address");
        assertThat(result).isNull();
    }
}
