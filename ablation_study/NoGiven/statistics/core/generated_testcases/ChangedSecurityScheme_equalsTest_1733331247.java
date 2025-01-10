
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;

public class ChangedSecurityScheme_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        ChangedSecurityScheme scheme = new ChangedSecurityScheme(new SecurityScheme(), new SecurityScheme(), null);
        assertTrue(scheme.equals(scheme));
    }

    @Test
    public void testEquals_NullObject() {
        ChangedSecurityScheme scheme = new ChangedSecurityScheme(new SecurityScheme(), new SecurityScheme(), null);
        assertFalse(scheme.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        ChangedSecurityScheme scheme = new ChangedSecurityScheme(new SecurityScheme(), new SecurityScheme(), null);
        assertFalse(scheme.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentFields() {
        SecurityScheme oldScheme = new SecurityScheme();
        SecurityScheme newScheme = new SecurityScheme();
        newScheme.setType("apiKey");

        ChangedSecurityScheme scheme1 = new ChangedSecurityScheme(oldScheme, newScheme, null);
        ChangedSecurityScheme scheme2 = new ChangedSecurityScheme(oldScheme, newScheme, null);

        assertTrue(scheme1.equals(scheme2));
    }

    @Test
    public void testEquals_DifferentSecuritySchemes() {
        SecurityScheme oldScheme1 = new SecurityScheme();
        SecurityScheme newScheme1 = new SecurityScheme();
        newScheme1.setType("apiKey");

        SecurityScheme oldScheme2 = new SecurityScheme();
        SecurityScheme newScheme2 = new SecurityScheme();
        newScheme2.setType("http");

        ChangedSecurityScheme scheme1 = new ChangedSecurityScheme(oldScheme1, newScheme1, null);
        ChangedSecurityScheme scheme2 = new ChangedSecurityScheme(oldScheme2, newScheme2, null);

        assertFalse(scheme1.equals(scheme2));
    }
}
