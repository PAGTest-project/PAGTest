
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.OpenAPI;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOpenApi_toStringTest {

    private ChangedOpenApi changedOpenApi;
    private OpenAPI oldSpecOpenApi;
    private OpenAPI newSpecOpenApi;
    private List<Endpoint> newEndpoints;
    private List<Endpoint> missingEndpoints;
    private List<ChangedOperation> changedOperations;
    private ChangedExtensions changedExtensions;

    @BeforeEach
    public void setUp() {
        CompositeConfiguration config = new CompositeConfiguration();
        OpenApiDiffOptions options = OpenApiDiffOptions.from(config);
        changedOpenApi = new ChangedOpenApi(options);

        oldSpecOpenApi = new OpenAPI();
        newSpecOpenApi = new OpenAPI();
        newEndpoints = Collections.emptyList();
        missingEndpoints = Collections.emptyList();
        changedOperations = Collections.emptyList();
        changedExtensions = new ChangedExtensions(Collections.emptyMap(), Collections.emptyMap(), null);

        changedOpenApi.setOldSpecOpenApi(oldSpecOpenApi);
        changedOpenApi.setNewSpecOpenApi(newSpecOpenApi);
        changedOpenApi.setNewEndpoints(newEndpoints);
        changedOpenApi.setMissingEndpoints(missingEndpoints);
        changedOpenApi.setChangedOperations(changedOperations);
        changedOpenApi.setChangedExtensions(changedExtensions);
    }

    @Test
    public void testToStringWithNoChanges() {
        String expected = "ChangedOpenApi(oldSpecOpenApi=io.swagger.v3.oas.models.OpenAPI@0, newSpecOpenApi=io.swagger.v3.oas.models.OpenAPI@0, newEndpoints=[], missingEndpoints=[], changedOperations=[], changedExtensions=org.openapitools.openapidiff.core.model.ChangedExtensions@0)";
        assertEquals(expected, changedOpenApi.toString());
    }

    @Test
    public void testToStringWithChanges() {
        newEndpoints = Collections.singletonList(new Endpoint());
        missingEndpoints = Collections.singletonList(new Endpoint());
        changedOperations = Collections.singletonList(new ChangedOperation("", null, null, null));
        changedExtensions = new ChangedExtensions(Collections.emptyMap(), Collections.emptyMap(), null);

        changedOpenApi.setNewEndpoints(newEndpoints);
        changedOpenApi.setMissingEndpoints(missingEndpoints);
        changedOpenApi.setChangedOperations(changedOperations);
        changedOpenApi.setChangedExtensions(changedExtensions);

        String expected = "ChangedOpenApi(oldSpecOpenApi=io.swagger.v3.oas.models.OpenAPI@0, newSpecOpenApi=io.swagger.v3.oas.models.OpenAPI@0, newEndpoints=[org.openapitools.openapidiff.core.model.Endpoint@0], missingEndpoints=[org.openapitools.openapidiff.core.model.Endpoint@0], changedOperations=[org.openapitools.openapidiff.core.model.ChangedOperation@0], changedExtensions=org.openapitools.openapidiff.core.model.ChangedExtensions@0)";
        assertEquals(expected, changedOpenApi.toString());
    }
}
