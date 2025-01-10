
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorResources_addFormSetTest {

    private ValidatorResources validatorResources;

    @BeforeEach
    public void setUp() {
        validatorResources = new ValidatorResources();
    }

    @Test
    public void testAddFormSetDefault() {
        FormSet fs = new FormSet();
        fs.setLanguage("");
        fs.setCountry("");
        fs.setVariant("");

        validatorResources.addFormSet(fs);

        assertNotNull(validatorResources.defaultFormSet, "Default FormSet should be set.");
    }

    @Test
    public void testAddFormSetWithLocale() {
        FormSet fs = new FormSet();
        fs.setLanguage("en");
        fs.setCountry("US");
        fs.setVariant("");

        validatorResources.addFormSet(fs);

        String key = validatorResources.buildKey(fs);
        assertNotNull(validatorResources.getFormSets().get(key), "FormSet should be added with locale key.");
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

        validatorResources.addFormSet(fs1);
        validatorResources.addFormSet(fs2);

        assertNotNull(validatorResources.defaultFormSet, "Default FormSet should be overridden.");
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

        validatorResources.addFormSet(fs1);
        validatorResources.addFormSet(fs2);

        String key = validatorResources.buildKey(fs1);
        assertNotNull(validatorResources.getFormSets().get(key), "FormSet should be overridden with locale key.");
    }
}
