
package net.datafaker.service;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FakeValuesContext_equalsTest {

    @Test
    void testEquals_SameInstance() {
        FakeValuesContext context = FakeValuesContext.of(Locale.US);
        assertTrue(context.equals(context));
    }

    @Test
    void testEquals_DifferentClass() {
        FakeValuesContext context = FakeValuesContext.of(Locale.US);
        assertFalse(context.equals(new Object()));
    }

    @Test
    void testEquals_Null() {
        FakeValuesContext context = FakeValuesContext.of(Locale.US);
        assertFalse(context.equals(null));
    }

    @Test
    void testEquals_DifferentLocale() {
        FakeValuesContext context1 = FakeValuesContext.of(Locale.US);
        FakeValuesContext context2 = FakeValuesContext.of(Locale.UK);
        assertFalse(context1.equals(context2));
    }

    @Test
    void testEquals_DifferentFilename() {
        FakeValuesContext context1 = FakeValuesContext.of(Locale.US, "file1", "path1");
        FakeValuesContext context2 = FakeValuesContext.of(Locale.US, "file2", "path1");
        assertFalse(context1.equals(context2));
    }

    @Test
    void testEquals_DifferentPath() {
        FakeValuesContext context1 = FakeValuesContext.of(Locale.US, "file1", "path1");
        FakeValuesContext context2 = FakeValuesContext.of(Locale.US, "file1", "path2");
        assertFalse(context1.equals(context2));
    }

    @Test
    void testEquals_DifferentUrl() throws Exception {
        URL url1 = new URL("http://example.com/1");
        URL url2 = new URL("http://example.com/2");
        FakeValuesContext context1 = FakeValuesContext.of(Locale.US, "file1", "path1", url1);
        FakeValuesContext context2 = FakeValuesContext.of(Locale.US, "file1", "path1", url2);
        assertFalse(context1.equals(context2));
    }

    @Test
    void testEquals_AllFieldsEqual() throws Exception {
        URL url = new URL("http://example.com");
        FakeValuesContext context1 = FakeValuesContext.of(Locale.US, "file1", "path1", url);
        FakeValuesContext context2 = FakeValuesContext.of(Locale.US, "file1", "path1", url);
        assertTrue(context1.equals(context2));
    }
}
