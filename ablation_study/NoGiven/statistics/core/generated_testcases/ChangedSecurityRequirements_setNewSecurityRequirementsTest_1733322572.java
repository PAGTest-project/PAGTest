
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedSecurityRequirements_setNewSecurityRequirementsTest {

    @Test
    public void testSetNewSecurityRequirements() {
        // Given
        List<SecurityRequirement> oldSecurityRequirements = Arrays.asList(new SecurityRequirement());
        List<SecurityRequirement> newSecurityRequirements = Arrays.asList(new SecurityRequirement());
        DiffContext context = new DiffContext();
        ChangedSecurityRequirements changedSecurityRequirements = new ChangedSecurityRequirements(oldSecurityRequirements, newSecurityRequirements, context);

        // When
        List<SecurityRequirement> updatedSecurityRequirements = Arrays.asList(new SecurityRequirement(), new SecurityRequirement());
        changedSecurityRequirements.setNewSecurityRequirements(updatedSecurityRequirements);

        // Then
        assertEquals(updatedSecurityRequirements, changedSecurityRequirements.getNewSecurityRequirements());
    }
}
