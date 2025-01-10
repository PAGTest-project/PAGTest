
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedOpenApi_setNewEndpointsTest {

    private ChangedOpenApi changedOpenApi;
    private List<Endpoint> newEndpoints;

    @BeforeEach
    public void setUp() {
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        changedOpenApi = new ChangedOpenApi(options);
        newEndpoints = Arrays.asList(
            new Endpoint("path1", null, null, null, null),
            new Endpoint("path2", null, null, null, null)
        );
    }

    @Test
    public void testSetNewEndpoints() {
        ChangedOpenApi result = changedOpenApi.setNewEndpoints(newEndpoints);

        assertNotNull(result);
        assertEquals(changedOpenApi, result);
        assertEquals(newEndpoints, changedOpenApi.getNewEndpoints());
    }
}
