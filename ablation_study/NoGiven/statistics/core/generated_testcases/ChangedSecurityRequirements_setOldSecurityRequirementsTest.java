
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangedSecurityRequirements_setOldSecurityRequirementsTest {

    @Test
    public void testSetOldSecurityRequirements() {
        // Given
        List<SecurityRequirement> oldSecurityRequirements = Arrays.asList(new SecurityRequirement());
        ChangedSecurityRequirements changedSecurityRequirements = new ChangedSecurityRequirements(null, null, null);

        // When
        changedSecurityRequirements.setOldSecurityRequirements(oldSecurityRequirements);

        // Then
        assertEquals(oldSecurityRequirements, changedSecurityRequirements.getOldSecurityRequirements());
    }

    @Test
    public void testSetOldSecurityRequirementsWithStateDependency() {
        // Given
        List<SecurityRequirement> oldSecurityRequirements = Arrays.asList(new SecurityRequirement());
        List<SecurityRequirement> newSecurityRequirements = Arrays.asList(new SecurityRequirement());
        ChangedSecurityRequirements changedSecurityRequirements = new ChangedSecurityRequirements(null, null, null);

        // When
        changedSecurityRequirements.setNewSecurityRequirements(newSecurityRequirements);
        changedSecurityRequirements.setOldSecurityRequirements(oldSecurityRequirements);

        // Then
        assertEquals(oldSecurityRequirements, changedSecurityRequirements.getOldSecurityRequirements());
        assertEquals(newSecurityRequirements, changedSecurityRequirements.getNewSecurityRequirements());
    }

    @Test
    public void testSetOldSecurityRequirementsWithMissingRequirements() {
        // Given
        List<SecurityRequirement> oldSecurityRequirements = Arrays.asList(new SecurityRequirement());
        List<SecurityRequirement> missingRequirements = Arrays.asList(new SecurityRequirement());
        ChangedSecurityRequirements changedSecurityRequirements = new ChangedSecurityRequirements(null, null, null);

        // When
        changedSecurityRequirements.setOldSecurityRequirements(oldSecurityRequirements);
        changedSecurityRequirements.setMissing(missingRequirements);

        // Then
        assertEquals(oldSecurityRequirements, changedSecurityRequirements.getOldSecurityRequirements());
        assertEquals(missingRequirements, changedSecurityRequirements.getMissing());
    }

    @Test
    public void testSetOldSecurityRequirementsWithCoreChanged() {
        // Given
        List<SecurityRequirement> oldSecurityRequirements = Arrays.asList(new SecurityRequirement());
        ChangedSecurityRequirements changedSecurityRequirements = new ChangedSecurityRequirements(null, null, null);

        // When
        changedSecurityRequirements.setOldSecurityRequirements(oldSecurityRequirements);

        // Then
        assertEquals(oldSecurityRequirements, changedSecurityRequirements.getOldSecurityRequirements());
        assertTrue(changedSecurityRequirements.isCoreChanged() == DiffResult.NO_CHANGES);
    }

    @Test
    public void testSetOldSecurityRequirementsWithEquals() {
        // Given
        List<SecurityRequirement> oldSecurityRequirements = Arrays.asList(new SecurityRequirement());
        ChangedSecurityRequirements changedSecurityRequirements = new ChangedSecurityRequirements(null, null, null);
        ChangedSecurityRequirements changedSecurityRequirements2 = new ChangedSecurityRequirements(null, null, null);

        // When
        changedSecurityRequirements.setOldSecurityRequirements(oldSecurityRequirements);
        changedSecurityRequirements2.setOldSecurityRequirements(oldSecurityRequirements);

        // Then
        assertEquals(oldSecurityRequirements, changedSecurityRequirements.getOldSecurityRequirements());
        assertTrue(changedSecurityRequirements.equals(changedSecurityRequirements2));
    }
}
