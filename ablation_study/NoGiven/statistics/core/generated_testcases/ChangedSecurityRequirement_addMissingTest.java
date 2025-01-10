
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChangedSecurityRequirement_addMissingTest {

    @Test
    public void testAddMissing_InitialMissingIsNull() {
        // Given
        ChangedSecurityRequirement changedSecurityRequirement = new ChangedSecurityRequirement(null, null, null);
        String key = "testKey";
        List<String> scopes = Arrays.asList("scope1", "scope2");

        // When
        changedSecurityRequirement.addMissing(key, scopes);

        // Then
        SecurityRequirement missing = changedSecurityRequirement.getMissing();
        assertNotNull(missing);
        assertEquals(scopes, missing.get(key));
    }

    @Test
    public void testAddMissing_InitialMissingIsNotNull() {
        // Given
        SecurityRequirement initialMissing = new SecurityRequirement();
        initialMissing.put("initialKey", Arrays.asList("initialScope1", "initialScope2"));
        ChangedSecurityRequirement changedSecurityRequirement = new ChangedSecurityRequirement(null, null, null);
        changedSecurityRequirement.setMissing(initialMissing);
        String key = "testKey";
        List<String> scopes = Arrays.asList("scope1", "scope2");

        // When
        changedSecurityRequirement.addMissing(key, scopes);

        // Then
        SecurityRequirement missing = changedSecurityRequirement.getMissing();
        assertNotNull(missing);
        assertEquals(scopes, missing.get(key));
        assertEquals(Arrays.asList("initialScope1", "initialScope2"), missing.get("initialKey"));
    }
}
