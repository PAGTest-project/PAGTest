
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
            this.getClass().getResourceAsStream("ValidatorResourcesTest-1-config.xml"),
            this.getClass().getResourceAsStream("ValidatorResourcesTest-2-config.xml")
        };

        resources = new ValidatorResources(streams);

        for (final InputStream stream : streams) {
            stream.close();
        }
    }

    @Test
    public void testGetFormExactMatch() {
        final Form form = resources.getForm("en", "US", "WIN", "testForm1");
        assertNotNull(form, "Form 'testForm1' not found for locale 'en_US_WIN'");
    }

    @Test
    public void testGetFormLanguageCountryMatch() {
        final Form form = resources.getForm("en", "US", null, "testForm1");
        assertNotNull(form, "Form 'testForm1' not found for locale 'en_US'");
    }

    @Test
    public void testGetFormLanguageMatch() {
        final Form form = resources.getForm("en", null, null, "testForm1");
        assertNotNull(form, "Form 'testForm1' not found for locale 'en'");
    }

    @Test
    public void testGetFormDefaultMatch() {
        final Form form = resources.getForm("xx", "YY", "ZZZ", "testForm1");
        assertNotNull(form, "Form 'testForm1' not found for default locale");
    }

    @Test
    public void testGetFormNotFound() {
        final Form form = resources.getForm("xx", "YY", "ZZZ", "nonExistentForm");
        assertNull(form, "Form 'nonExistentForm' should not be found");
    }
}
