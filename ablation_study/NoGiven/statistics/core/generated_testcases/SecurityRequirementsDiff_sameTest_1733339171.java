
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecurityRequirementsDiff_sameTest {

    private SecurityRequirementsDiff securityRequirementsDiff;
    private Components leftComponents;
    private Components rightComponents;

    @BeforeEach
    public void setUp() {
        OpenApiDiff openApiDiff = Mockito.mock(OpenApiDiff.class);
        leftComponents = new Components();
        rightComponents = new Components();
        Mockito.when(openApiDiff.getOldSpecOpenApi()).thenReturn(null);
        Mockito.when(openApiDiff.getNewSpecOpenApi()).thenReturn(null);
        securityRequirementsDiff = new SecurityRequirementsDiff(openApiDiff);
        securityRequirementsDiff.leftComponents = leftComponents;
        securityRequirementsDiff.rightComponents = rightComponents;
    }

    @Test
    public void testSame_EqualSecurityRequirements() {
        SecurityRequirement left = new SecurityRequirement();
        SecurityRequirement right = new SecurityRequirement();

        left.addList("scheme1", Collections.singletonList("scope1"));
        right.addList("scheme1", Collections.singletonList("scope1"));

        leftComponents.addSecuritySchemes("scheme1", new SecurityScheme().type(SecurityScheme.Type.HTTP).in(SecurityScheme.In.HEADER));
        rightComponents.addSecuritySchemes("scheme1", new SecurityScheme().type(SecurityScheme.Type.HTTP).in(SecurityScheme.In.HEADER));

        assertTrue(securityRequirementsDiff.same(left, right));
    }

    @Test
    public void testSame_DifferentSecurityRequirements() {
        SecurityRequirement left = new SecurityRequirement();
        SecurityRequirement right = new SecurityRequirement();

        left.addList("scheme1", Collections.singletonList("scope1"));
        right.addList("scheme2", Collections.singletonList("scope2"));

        leftComponents.addSecuritySchemes("scheme1", new SecurityScheme().type(SecurityScheme.Type.HTTP).in(SecurityScheme.In.HEADER));
        rightComponents.addSecuritySchemes("scheme2", new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.QUERY));

        assertFalse(securityRequirementsDiff.same(left, right));
    }
}
