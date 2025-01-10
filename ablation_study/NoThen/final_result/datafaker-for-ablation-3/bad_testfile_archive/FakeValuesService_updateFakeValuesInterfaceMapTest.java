
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
        SingletonLocale localeEn = SingletonLocale.get(Locale.ENGLISH);
        SingletonLocale localeFr = SingletonLocale.get(Locale.FRENCH);
        List<SingletonLocale> locales = Arrays.asList(localeEn, localeFr);

        fakeValuesService.updateFakeValuesInterfaceMap(locales);

        assertEquals(2, fakeValuesService.fakeValuesInterfaceMap.size());
    }

    @Test
    public void testUpdateFakeValuesInterfaceMapWithEmptyList() {
        List<SingletonLocale> locales = Arrays.asList();

        fakeValuesService.updateFakeValuesInterfaceMap(locales);

        assertEquals(0, fakeValuesService.fakeValuesInterfaceMap.size());
    }
}
