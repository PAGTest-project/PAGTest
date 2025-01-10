
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
        assertThat(fakeValuesGrouping.get("address")).isEqualTo(addressValues.get("address")).isNotNull();
    }

    @Test
    void testAddFakeValuesGrouping() {
        FakeValuesGrouping anotherGrouping = new FakeValuesGrouping();
        FakeValues nameValues = FakeValues.of(FakeValuesContext.of(Locale.ENGLISH, "name.yml", "name"));
        anotherGrouping.add(nameValues);

        fakeValuesGrouping.add(anotherGrouping);
        assertThat(fakeValuesGrouping.get("name")).isEqualTo(nameValues.get("name")).isNotNull();
    }

    @Test
    void testAddUnsupportedFakeValue() {
        FakeValuesInterface unsupportedFakeValue = new FakeValuesInterface() {};
        assertThrows(RuntimeException.class, () -> fakeValuesGrouping.add(unsupportedFakeValue));
    }
}
