
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedSecurityRequirement_hashCodeTest {

    @Test
    public void testHashCode() {
        SecurityRequirement oldSecurityRequirement = new SecurityRequirement();
        SecurityRequirement newSecurityRequirement = new SecurityRequirement();
        SecurityRequirement missing = new SecurityRequirement();
        SecurityRequirement increased = new SecurityRequirement();
        ChangedSecurityScheme changedSecurityScheme = new ChangedSecurityScheme();
        ChangedSecurityRequirement changedSecurityRequirement = new ChangedSecurityRequirement(oldSecurityRequirement, newSecurityRequirement, null)
                .setMissing(missing)
                .setIncreased(increased);
        changedSecurityRequirement.addChanged(changedSecurityScheme);

        int expectedHashCode = Objects.hash(oldSecurityRequirement, newSecurityRequirement, missing, increased, changedSecurityRequirement.getChanged());
        assertEquals(expectedHashCode, changedSecurityRequirement.hashCode());
    }
}
