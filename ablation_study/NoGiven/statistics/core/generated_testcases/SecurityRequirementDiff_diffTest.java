
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedSecurityRequirement;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SecurityRequirementDiff_diffTest {

    @Test
    public void testDiff_LeftNullRightNull() {
        SecurityRequirementDiff securityRequirementDiff = new SecurityRequirementDiff(Mockito.mock(OpenApiDiff.class));
        DiffContext context = Mockito.mock(DiffContext.class);

        DeferredChanged<ChangedSecurityRequirement> result = securityRequirementDiff.diff(null, null, context);

        assertTrue(result.isPresent());
        ChangedSecurityRequirement changedSecurityRequirement = result.get();
        assertTrue(changedSecurityRequirement.getMissing().isEmpty());
        assertTrue(changedSecurityRequirement.getIncreased().isEmpty());
    }

    @Test
    public void testDiff_LeftNotNullRightNull() {
        SecurityRequirementDiff securityRequirementDiff = new SecurityRequirementDiff(Mockito.mock(OpenApiDiff.class));
        DiffContext context = Mockito.mock(DiffContext.class);
        SecurityRequirement left = new SecurityRequirement();
        left.put("scheme1", Arrays.asList("scope1", "scope2"));

        DeferredChanged<ChangedSecurityRequirement> result = securityRequirementDiff.diff(left, null, context);

        assertTrue(result.isPresent());
        ChangedSecurityRequirement changedSecurityRequirement = result.get();
        assertTrue(changedSecurityRequirement.getMissing().containsKey("scheme1"));
        assertTrue(changedSecurityRequirement.getIncreased().isEmpty());
    }

    @Test
    public void testDiff_LeftNullRightNotNull() {
        SecurityRequirementDiff securityRequirementDiff = new SecurityRequirementDiff(Mockito.mock(OpenApiDiff.class));
        DiffContext context = Mockito.mock(DiffContext.class);
        SecurityRequirement right = new SecurityRequirement();
        right.put("scheme1", Arrays.asList("scope1", "scope2"));

        DeferredChanged<ChangedSecurityRequirement> result = securityRequirementDiff.diff(null, right, context);

        assertTrue(result.isPresent());
        ChangedSecurityRequirement changedSecurityRequirement = result.get();
        assertTrue(changedSecurityRequirement.getMissing().isEmpty());
        assertTrue(changedSecurityRequirement.getIncreased().containsKey("scheme1"));
    }

    @Test
    public void testDiff_LeftAndRightNotNull() {
        SecurityRequirementDiff securityRequirementDiff = new SecurityRequirementDiff(Mockito.mock(OpenApiDiff.class));
        DiffContext context = Mockito.mock(DiffContext.class);
        SecurityRequirement left = new SecurityRequirement();
        left.put("scheme1", Arrays.asList("scope1", "scope2"));
        SecurityRequirement right = new SecurityRequirement();
        right.put("scheme1", Arrays.asList("scope1", "scope2"));

        DeferredChanged<ChangedSecurityRequirement> result = securityRequirementDiff.diff(left, right, context);

        assertTrue(result.isPresent());
        ChangedSecurityRequirement changedSecurityRequirement = result.get();
        assertTrue(changedSecurityRequirement.getMissing().isEmpty());
        assertTrue(changedSecurityRequirement.getIncreased().isEmpty());
    }

    @Test
    public void testDiff_LeftAndRightDifferent() {
        SecurityRequirementDiff securityRequirementDiff = new SecurityRequirementDiff(Mockito.mock(OpenApiDiff.class));
        DiffContext context = Mockito.mock(DiffContext.class);
        SecurityRequirement left = new SecurityRequirement();
        left.put("scheme1", Arrays.asList("scope1", "scope2"));
        SecurityRequirement right = new SecurityRequirement();
        right.put("scheme2", Arrays.asList("scope3", "scope4"));

        DeferredChanged<ChangedSecurityRequirement> result = securityRequirementDiff.diff(left, right, context);

        assertTrue(result.isPresent());
        ChangedSecurityRequirement changedSecurityRequirement = result.get();
        assertTrue(changedSecurityRequirement.getMissing().containsKey("scheme1"));
        assertTrue(changedSecurityRequirement.getIncreased().containsKey("scheme2"));
    }
}
