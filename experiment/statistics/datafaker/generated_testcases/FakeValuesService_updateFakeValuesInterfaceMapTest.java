
package net.datafaker.service;

import net.datafaker.internal.helper.SingletonLocale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FakeValuesService_updateFakeValuesInterfaceMapTest {

    private FakeValuesService fakeValuesService;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
    }

    @Test
    public void testUpdateFakeValuesInterfaceMap() {
        SingletonLocale locale1 = SingletonLocale.get(Locale.ENGLISH);
        SingletonLocale locale2 = SingletonLocale.get(Locale.US);
        List<SingletonLocale> locales = Arrays.asList(locale1, locale2);

        fakeValuesService.updateFakeValuesInterfaceMap(locales);

        // Assuming getCachedFakeValue returns a non-null value for each locale
        assertEquals(2, fakeValuesService.getFakeValuesInterfaceMap().size());
    }
}
