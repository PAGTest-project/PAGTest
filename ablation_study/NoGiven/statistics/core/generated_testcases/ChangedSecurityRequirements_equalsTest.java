
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangedSecurityRequirements_equalsTest {

    @Test
    public void testEquals_SameObject() {
        ChangedSecurityRequirements obj = new ChangedSecurityRequirements(null, null, null);
        assertTrue(obj.equals(obj));
    }

    @Test
    public void testEquals_NullObject() {
        ChangedSecurityRequirements obj = new ChangedSecurityRequirements(null, null, null);
        assertFalse(obj.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        ChangedSecurityRequirements obj = new ChangedSecurityRequirements(null, null, null);
        assertFalse(obj.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentFields() {
        List<SecurityRequirement> oldReqs = Arrays.asList(new SecurityRequirement());
        List<SecurityRequirement> newReqs = Arrays.asList(new SecurityRequirement());
        ChangedSecurityRequirements obj1 = new ChangedSecurityRequirements(oldReqs, newReqs, null);
        ChangedSecurityRequirements obj2 = new ChangedSecurityRequirements(null, null, null);
        assertFalse(obj1.equals(obj2));
    }

    @Test
    public void testEquals_SameFields() {
        List<SecurityRequirement> oldReqs = Arrays.asList(new SecurityRequirement());
        List<SecurityRequirement> newReqs = Arrays.asList(new SecurityRequirement());
        ChangedSecurityRequirements obj1 = new ChangedSecurityRequirements(oldReqs, newReqs, null);
        ChangedSecurityRequirements obj2 = new ChangedSecurityRequirements(oldReqs, newReqs, null);
        assertTrue(obj1.equals(obj2));
    }
}
