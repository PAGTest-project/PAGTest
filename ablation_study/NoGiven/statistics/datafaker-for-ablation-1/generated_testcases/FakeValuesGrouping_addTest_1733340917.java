
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        assertThat(fakeValuesGrouping.get("address")).isEqualTo(addressValues.get("address"))
            .isNotNull();
    }

    @Test
    void testAddFakeValuesGrouping() {
        FakeValuesGrouping anotherGrouping = new FakeValuesGrouping();
        FakeValues catValues = FakeValues.of(FakeValuesContext.of(Locale.ENGLISH, "cat.yml", "creature"));
        anotherGrouping.add(catValues);

        fakeValuesGrouping.add(anotherGrouping);

        assertThat(fakeValuesGrouping.get("creature")).isEqualTo(catValues.get("creature"))
            .isNotNull();
    }

    @Test
    void testAddUnsupportedFakeValues() {
        FakeValuesInterface unsupportedFakeValue = new FakeValuesInterface() {
            @Override
            public Object get(String key) {
                return null;
            }
        };

        assertThrows(RuntimeException.class, () -> fakeValuesGrouping.add(unsupportedFakeValue));
    }
}
