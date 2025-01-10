
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

class FakeValuesGrouping_addTest {

    @Test
    void testAddWithFakeValues() {
        FakeValuesGrouping grouping = new FakeValuesGrouping();
        FakeValues fakeValue = FakeValues.of(FakeValuesContext.of(Locale.ENGLISH, "file", "path"));

        grouping.add(fakeValue);

        assertNotNull(grouping.get("path"));
    }

    @Test
    void testAddWithFakeValuesGrouping() {
        FakeValuesGrouping grouping1 = new FakeValuesGrouping();
        FakeValuesGrouping grouping2 = new FakeValuesGrouping();
        FakeValues fakeValue = FakeValues.of(FakeValuesContext.of(Locale.ENGLISH, "file", "path"));
        grouping2.add(fakeValue);

        grouping1.add(grouping2);

        assertNotNull(grouping1.get("path"));
    }

    @Test
    void testAddWithUnsupportedType() {
        FakeValuesGrouping grouping = new FakeValuesGrouping();
        FakeValuesInterface unsupportedValue = new FakeValuesInterface() {};

        Exception exception = assertThrows(RuntimeException.class, () -> {
            grouping.add(unsupportedValue);
        });

        String expectedMessage = "net.datafaker.service.FakeValuesGroupingTest$1 not supported (please raise an issue)";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
