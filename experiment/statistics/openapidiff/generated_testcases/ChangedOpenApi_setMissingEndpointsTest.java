
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.apache.commons.configuration2.CompositeConfiguration;

public class ChangedOpenApi_setMissingEndpointsTest {

    private ChangedOpenApi changedOpenApi;
    private OpenApiDiffOptions options;

    @BeforeEach
    public void setUp() {
        CompositeConfiguration config = new CompositeConfiguration();
        options = new OpenApiDiffOptions(config);
        changedOpenApi = new ChangedOpenApi(options);
    }

    @Test
    public void testSetMissingEndpoints() {
        // Given
        List<Endpoint> missingEndpoints = Arrays.asList(
            new Endpoint(),
            new Endpoint()
        );

        // When
        changedOpenApi.setMissingEndpoints(missingEndpoints);

        // Then
        List<Endpoint> retrievedMissingEndpoints = changedOpenApi.getMissingEndpoints();
        assertNotNull(retrievedMissingEndpoints);
        assertEquals(missingEndpoints, retrievedMissingEndpoints);
    }
}
