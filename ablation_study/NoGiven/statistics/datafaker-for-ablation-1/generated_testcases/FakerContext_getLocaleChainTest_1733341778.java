
package net.datafaker.service;

import net.datafaker.internal.helper.SingletonLocale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FakerContext_getLocaleChainTest {

    private FakerContext fakerContext;

    @BeforeEach
    public void setUp() {
        fakerContext = new FakerContext(Locale.US, null);
    }

    @Test
    public void testGetLocaleChain_WithExistingLocale() {
        // Given
        SingletonLocale locale = SingletonLocale.get(Locale.US);
        FakerContext.LOCALE_2_LOCALES_CHAIN.put(locale, List.of(locale));

        // When
        List<SingletonLocale> result = fakerContext.getLocaleChain();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(locale, result.get(0));
    }

    @Test
    public void testGetLocaleChain_WithNonExistingLocale() {
        // Given
        SingletonLocale locale = SingletonLocale.get(Locale.FRANCE);
        FakerContext.LOCALE_2_LOCALES_CHAIN.clear();

        // When
        fakerContext.setLocale(Locale.FRANCE);
        List<SingletonLocale> result = fakerContext.getLocaleChain();

        // Then
        assertNull(result);
    }
}
