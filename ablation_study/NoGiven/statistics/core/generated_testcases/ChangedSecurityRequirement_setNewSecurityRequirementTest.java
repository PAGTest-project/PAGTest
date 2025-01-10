
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedSecurityRequirement_setNewSecurityRequirementTest {

    @Test
    public void testSetNewSecurityRequirement() {
        // Given
        SecurityRequirement oldSecurityRequirement = new SecurityRequirement();
        SecurityRequirement newSecurityRequirement = new SecurityRequirement();
        DiffContext context = new DiffContext(new OpenApiDiffOptions());
        ChangedSecurityRequirement changedSecurityRequirement = new ChangedSecurityRequirement(oldSecurityRequirement, null, context);

        // When
        changedSecurityRequirement.setOldSecurityRequirement(oldSecurityRequirement);
        changedSecurityRequirement.setNewSecurityRequirement(newSecurityRequirement);

        // Then
        assertEquals(newSecurityRequirement, changedSecurityRequirement.getNewSecurityRequirement());
        assertEquals(DiffResult.COMPATIBLE, changedSecurityRequirement.isCoreChanged());
    }
}
