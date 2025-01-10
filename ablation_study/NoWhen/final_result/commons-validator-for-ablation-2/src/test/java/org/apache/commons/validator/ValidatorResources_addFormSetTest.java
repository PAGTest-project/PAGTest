
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
        FormSet formSet = new FormSet();
        formSet.setLanguage("");
        formSet.setCountry("");
        formSet.setVariant("");

        validatorResources.addFormSet(formSet);

        assertEquals(formSet, validatorResources.defaultFormSet);
    }

    @Test
    public void testAddFormSetWithLocale() {
        FormSet formSet = new FormSet();
        formSet.setLanguage("en");
        formSet.setCountry("US");
        formSet.setVariant("");

        validatorResources.addFormSet(formSet);

        String key = validatorResources.buildKey(formSet);
        assertEquals(formSet, validatorResources.getFormSets().get(key));
    }

    @Test
    public void testAddFormSetOverrideDefault() {
        FormSet formSet1 = new FormSet();
        formSet1.setLanguage("");
        formSet1.setCountry("");
        formSet1.setVariant("");

        FormSet formSet2 = new FormSet();
        formSet2.setLanguage("");
        formSet2.setCountry("");
        formSet2.setVariant("");

        validatorResources.addFormSet(formSet1);
        validatorResources.addFormSet(formSet2);

        assertEquals(formSet2, validatorResources.defaultFormSet);
    }

    @Test
    public void testAddFormSetOverrideExisting() {
        FormSet formSet1 = new FormSet();
        formSet1.setLanguage("en");
        formSet1.setCountry("US");
        formSet1.setVariant("");

        FormSet formSet2 = new FormSet();
        formSet2.setLanguage("en");
        formSet2.setCountry("US");
        formSet2.setVariant("");

        validatorResources.addFormSet(formSet1);
        validatorResources.addFormSet(formSet2);

        String key = validatorResources.buildKey(formSet1);
        assertEquals(formSet2, validatorResources.getFormSets().get(key));
    }
}
