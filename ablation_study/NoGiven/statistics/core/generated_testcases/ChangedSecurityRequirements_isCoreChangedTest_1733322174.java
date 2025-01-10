
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import java.util.Collections;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.compare.SecurityRequirementsDiff;

public class ChangedSecurityRequirements_isCoreChangedTest {

    private ChangedSecurityRequirements changedSecurityRequirements;
    private DiffContext context;

    @BeforeEach
    public void setUp() {
        context = mock(DiffContext.class);
        changedSecurityRequirements = new ChangedSecurityRequirements(
            Collections.emptyList(), Collections.emptyList(), context);
    }

    @Test
    public void testIsCoreChanged_NoChanges() {
        // Given
        changedSecurityRequirements.setMissing(Collections.emptyList());
        changedSecurityRequirements.setIncreased(Collections.emptyList());

        // When
        DiffResult result = changedSecurityRequirements.isCoreChanged();

        // Then
        assertEquals(DiffResult.NO_CHANGES, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible() {
        // Given
        SecurityRequirement missingRequirement = new SecurityRequirement();
        changedSecurityRequirements.addMissing(missingRequirement);
        when(SecurityRequirementsDiff.SECURITY_REQUIREMENTS_DECREASED.enabled(context)).thenReturn(true);

        // When
        DiffResult result = changedSecurityRequirements.isCoreChanged();

        // Then
        assertEquals(DiffResult.INCOMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Compatible() {
        // Given
        SecurityRequirement increasedRequirement = new SecurityRequirement();
        changedSecurityRequirements.addIncreased(increasedRequirement);
        when(SecurityRequirementsDiff.SECURITY_REQUIREMENTS_DECREASED.enabled(context)).thenReturn(false);

        // When
        DiffResult result = changedSecurityRequirements.isCoreChanged();

        // Then
        assertEquals(DiffResult.COMPATIBLE, result);
    }
}
