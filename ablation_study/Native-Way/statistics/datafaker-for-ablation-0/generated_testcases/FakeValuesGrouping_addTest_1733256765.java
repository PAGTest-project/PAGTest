
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesGrouping_addTest {
    private FakeValuesGrouping fakeValuesGrouping;
    private FakeValues addressValues;

    @BeforeEach
    void setUp() {
        fakeValuesGrouping = new FakeValuesGrouping();
        addressValues = FakeValues.of(FakeValuesContext.of(Locale.ENGLISH, "address.yml", "address"));
    }

    @Test
    void testAddFakeValues() {
        fakeValuesGrouping.add(addressValues);
        Set<String> paths = addressValues.getPaths();
        for (String path : paths) {
            assertNotNull(fakeValuesGrouping.get(path));
        }
    }

    @Test
    void testAddFakeValuesGrouping() {
        FakeValuesGrouping anotherGrouping = new FakeValuesGrouping();
        FakeValues nameValues = FakeValues.of(FakeValuesContext.of(Locale.ENGLISH, "name.yml", "name"));
        anotherGrouping.add(nameValues);

        fakeValuesGrouping.add(anotherGrouping);
        Set<String> paths = nameValues.getPaths();
        for (String path : paths) {
            assertNotNull(fakeValuesGrouping.get(path));
        }
    }

    @Test
    void testAddUnsupportedType() {
        FakeValuesInterface unsupportedFakeValue = new FakeValuesInterface() {
            @Override
            public Object get(String key) {
                return null;
            }
        };
        assertThrows(RuntimeException.class, () -> fakeValuesGrouping.add(unsupportedFakeValue));
    }
}
