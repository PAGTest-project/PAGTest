
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangedSecurityRequirement_setOldSecurityRequirementTest {

    @Test
    public void testSetOldSecurityRequirement() {
        // Given
        SecurityRequirement oldSecurityRequirement = new SecurityRequirement();
        ChangedSecurityRequirement changedSecurityRequirement = new ChangedSecurityRequirement(null, null, null);

        // When
        changedSecurityRequirement.setOldSecurityRequirement(oldSecurityRequirement);

        // Then
        assertEquals(oldSecurityRequirement, changedSecurityRequirement.getOldSecurityRequirement());
    }
}
