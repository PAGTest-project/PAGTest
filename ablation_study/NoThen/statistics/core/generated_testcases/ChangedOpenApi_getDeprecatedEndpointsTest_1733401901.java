
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.utils.EndpointUtils;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;

public class ChangedOpenApi_getDeprecatedEndpointsTest {

    private ChangedOpenApi changedOpenApi;
    private ChangedOperation deprecatedOperation;
    private ChangedOperation nonDeprecatedOperation;

    @BeforeEach
    public void setUp() {
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        changedOpenApi = new ChangedOpenApi(options);

        deprecatedOperation = mock(ChangedOperation.class);
        when(deprecatedOperation.isDeprecated()).thenReturn(true);
        when(deprecatedOperation.getPathUrl()).thenReturn("/deprecatedPath");
        when(deprecatedOperation.getHttpMethod()).thenReturn(PathItem.HttpMethod.GET);
        when(deprecatedOperation.getNewOperation()).thenReturn(mock(Operation.class));

        nonDeprecatedOperation = mock(ChangedOperation.class);
        when(nonDeprecatedOperation.isDeprecated()).thenReturn(false);

        changedOpenApi.setChangedOperations(Arrays.asList(deprecatedOperation, nonDeprecatedOperation));
    }

    @Test
    public void testGetDeprecatedEndpoints_WithDeprecatedOperation() {
        List<Endpoint> deprecatedEndpoints = changedOpenApi.getDeprecatedEndpoints();
        assertEquals(1, deprecatedEndpoints.size());
        Endpoint endpoint = deprecatedEndpoints.get(0);
        assertEquals("/deprecatedPath", endpoint.getPathUrl());
        assertEquals(PathItem.HttpMethod.GET, endpoint.getMethod());
    }

    @Test
    public void testGetDeprecatedEndpoints_WithoutDeprecatedOperation() {
        changedOpenApi.setChangedOperations(Arrays.asList(nonDeprecatedOperation));
        List<Endpoint> deprecatedEndpoints = changedOpenApi.getDeprecatedEndpoints();
        assertTrue(deprecatedEndpoints.isEmpty());
    }

    @Test
    public void testGetDeprecatedEndpoints_WithEmptyOperations() {
        changedOpenApi.setChangedOperations(Arrays.asList());
        List<Endpoint> deprecatedEndpoints = changedOpenApi.getDeprecatedEndpoints();
        assertTrue(deprecatedEndpoints.isEmpty());
    }
}
