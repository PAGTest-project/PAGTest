
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedSecurityRequirements_hashCodeTest {

    @Test
    public void testHashCode() {
        // Given
        SecurityRequirement oldReq = new SecurityRequirement();
        oldReq.addList("oldScheme", Arrays.asList("scope1", "scope2"));
        SecurityRequirement newReq = new SecurityRequirement();
        newReq.addList("newScheme", Arrays.asList("scope3", "scope4"));

        List<SecurityRequirement> oldSecurityRequirements = Arrays.asList(oldReq);
        List<SecurityRequirement> newSecurityRequirements = Arrays.asList(newReq);

        ChangedSecurityRequirements changedSecurityRequirements = new ChangedSecurityRequirements(
                oldSecurityRequirements, newSecurityRequirements, null);

        // When
        int hashCode = changedSecurityRequirements.hashCode();

        // Then
        ChangedSecurityRequirements expected = new ChangedSecurityRequirements(
                oldSecurityRequirements, newSecurityRequirements, null);
        assertEquals(expected.hashCode(), hashCode);
    }
}
