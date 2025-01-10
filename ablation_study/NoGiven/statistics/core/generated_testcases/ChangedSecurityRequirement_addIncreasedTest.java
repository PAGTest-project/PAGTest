
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChangedSecurityRequirement_addIncreasedTest {

    @Test
    public void testAddIncreased_IncreasedIsNull() {
        // Given
        ChangedSecurityRequirement changedSecurityRequirement = new ChangedSecurityRequirement(null, null, null);
        String key = "testKey";
        List<String> scopes = Arrays.asList("scope1", "scope2");

        // When
        changedSecurityRequirement.addIncreased(key, scopes);

        // Then
        SecurityRequirement increased = changedSecurityRequirement.getIncreased();
        assertNotNull(increased);
        assertEquals(scopes, increased.get(key));
    }

    @Test
    public void testAddIncreased_IncreasedIsNotNull() {
        // Given
        SecurityRequirement initialIncreased = new SecurityRequirement();
        initialIncreased.put("initialKey", Arrays.asList("initialScope1", "initialScope2"));
        ChangedSecurityRequirement changedSecurityRequirement = new ChangedSecurityRequirement(null, null, null);
        changedSecurityRequirement.setIncreased(initialIncreased);
        String key = "testKey";
        List<String> scopes = Arrays.asList("scope1", "scope2");

        // When
        changedSecurityRequirement.addIncreased(key, scopes);

        // Then
        SecurityRequirement increased = changedSecurityRequirement.getIncreased();
        assertNotNull(increased);
        assertEquals(scopes, increased.get(key));
        assertEquals(Arrays.asList("initialScope1", "initialScope2"), increased.get("initialKey"));
    }
}
