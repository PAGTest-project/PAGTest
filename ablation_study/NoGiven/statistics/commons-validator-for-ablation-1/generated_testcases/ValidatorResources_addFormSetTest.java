
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorResources_addFormSetTest {

    private ValidatorResources resources;

    @BeforeEach
    public void setUp() {
        resources = new ValidatorResources();
    }

    @Test
    public void testAddFormSetDefault() {
        FormSet fs = new FormSet();
        fs.setLanguage("");
        fs.setCountry("");
        fs.setVariant("");

        resources.addFormSet(fs);

        assertEquals(fs, resources.defaultFormSet, "Default FormSet should be set.");
    }

    @Test
    public void testAddFormSetWithLocale() {
        FormSet fs = new FormSet();
        fs.setLanguage("en");
        fs.setCountry("US");
        fs.setVariant("");

        resources.addFormSet(fs);

        String key = resources.buildKey(fs);
        assertEquals(fs, resources.getFormSets().get(key), "FormSet should be added with locale key.");
    }

    @Test
    public void testAddFormSetOverrideDefault() {
        FormSet fs1 = new FormSet();
        fs1.setLanguage("");
        fs1.setCountry("");
        fs1.setVariant("");

        FormSet fs2 = new FormSet();
        fs2.setLanguage("");
        fs2.setCountry("");
        fs2.setVariant("");

        resources.addFormSet(fs1);
        resources.addFormSet(fs2);

        assertEquals(fs2, resources.defaultFormSet, "Default FormSet should be overridden.");
    }

    @Test
    public void testAddFormSetOverrideWithLocale() {
        FormSet fs1 = new FormSet();
        fs1.setLanguage("en");
        fs1.setCountry("US");
        fs1.setVariant("");

        FormSet fs2 = new FormSet();
        fs2.setLanguage("en");
        fs2.setCountry("US");
        fs2.setVariant("");

        resources.addFormSet(fs1);
        resources.addFormSet(fs2);

        String key = resources.buildKey(fs1);
        assertEquals(fs2, resources.getFormSets().get(key), "FormSet should be overridden with locale key.");
    }

    @Test
    public void testAddFormSetNoOverride() {
        FormSet fs1 = new FormSet();
        fs1.setLanguage("en");
        fs1.setCountry("US");
        fs1.setVariant("");

        FormSet fs2 = new FormSet();
        fs2.setLanguage("en");
        fs2.setCountry("GB");
        fs2.setVariant("");

        resources.addFormSet(fs1);
        resources.addFormSet(fs2);

        String key1 = resources.buildKey(fs1);
        String key2 = resources.buildKey(fs2);
        assertEquals(fs1, resources.getFormSets().get(key1), "FormSet should not be overridden.");
        assertEquals(fs2, resources.getFormSets().get(key2), "FormSet should not be overridden.");
    }
}
