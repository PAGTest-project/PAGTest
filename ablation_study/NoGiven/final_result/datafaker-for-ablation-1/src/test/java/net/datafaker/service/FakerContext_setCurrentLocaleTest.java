
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FakerContext_setCurrentLocaleTest {

    private FakerContext fakerContext;

    @BeforeEach
    public void setUp() {
        fakerContext = new FakerContext(Locale.US, null);
    }

    @Test
    public void testSetCurrentLocale_NewLocale() {
        // Given
        Locale newLocale = Locale.FRANCE;

        // When
        fakerContext.setCurrentLocale(newLocale);

        // Then
        assertEquals(newLocale, fakerContext.getLocale());
        assertNotNull(fakerContext.getLocaleChain());
    }

    @Test
    public void testSetCurrentLocale_ExistingLocale() {
        // Given
        Locale existingLocale = Locale.US;

        // When
        fakerContext.setCurrentLocale(existingLocale);

        // Then
        assertEquals(existingLocale, fakerContext.getLocale());
        assertNotNull(fakerContext.getLocaleChain());
    }
}
