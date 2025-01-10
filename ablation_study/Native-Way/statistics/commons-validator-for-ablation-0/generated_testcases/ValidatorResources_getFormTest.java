
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

public class ValidatorResources_getFormTest {

    private ValidatorResources resources;

    @BeforeEach
    protected void setUp() throws IOException, SAXException {
        final InputStream[] streams = {
            this.getClass().getResourceAsStream("MultipleConfigFilesTest-1-config.xml"),
            this.getClass().getResourceAsStream("MultipleConfigFilesTest-2-config.xml")
        };

        resources = new ValidatorResources(streams);

        for (final InputStream stream : streams) {
            stream.close();
        }
    }

    @Test
    public void testGetFormWithLanguageCountryVariant() {
        final Form form = resources.getForm("fr", "FR", "VAR", "testForm1_fr");
        assertNotNull(form, "Form 'testForm1_fr' not found for locale 'fr_FR_VAR'");
    }

    @Test
    public void testGetFormWithLanguageCountry() {
        final Form form = resources.getForm("fr", "FR", null, "testForm1_fr");
        assertNotNull(form, "Form 'testForm1_fr' not found for locale 'fr_FR'");
    }

    @Test
    public void testGetFormWithLanguage() {
        final Form form = resources.getForm("fr", null, null, "testForm1_fr");
        assertNotNull(form, "Form 'testForm1_fr' not found for locale 'fr'");
    }

    @Test
    public void testGetFormWithDefaultLocale() {
        final Form form = resources.getForm("", "", "", "testForm1");
        assertNotNull(form, "Form 'testForm1' not found for default locale");
    }

    @Test
    public void testGetFormNotFound() {
        final Form form = resources.getForm("de", "DE", "VAR", "nonExistentForm");
        assertNull(form, "Form 'nonExistentForm' should not be found for locale 'de_DE_VAR'");
    }
}
