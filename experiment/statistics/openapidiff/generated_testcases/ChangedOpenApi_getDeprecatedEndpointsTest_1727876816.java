
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.utils.EndpointUtils;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Operation;

public class ChangedOpenApi_getDeprecatedEndpointsTest {

    private ChangedOpenApi changedOpenApi;
    private ChangedOperation deprecatedOperation;
    private ChangedOperation nonDeprecatedOperation;

    @BeforeEach
    public void setUp() {
        OpenApiDiffOptions options = new OpenApiDiffOptions(mock(org.apache.commons.configuration2.CompositeConfiguration.class));
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
    public void testGetDeprecatedEndpoints() {
        List<Endpoint> deprecatedEndpoints = changedOpenApi.getDeprecatedEndpoints();
        assertEquals(1, deprecatedEndpoints.size());
        Endpoint endpoint = deprecatedEndpoints.get(0);
        assertEquals("/deprecatedPath", endpoint.getPathUrl());
        assertEquals(PathItem.HttpMethod.GET, endpoint.getMethod());
    }
}
