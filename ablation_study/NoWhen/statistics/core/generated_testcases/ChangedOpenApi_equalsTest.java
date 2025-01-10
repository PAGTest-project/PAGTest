
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.apache.commons.configuration2.CompositeConfiguration;

public class ChangedOpenApi_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        ChangedOpenApi changedOpenApi = new ChangedOpenApi(new OpenApiDiffOptions(new CompositeConfiguration()));
        assertTrue(changedOpenApi.equals(changedOpenApi));
    }

    @Test
    public void testEquals_NullObject() {
        ChangedOpenApi changedOpenApi = new ChangedOpenApi(new OpenApiDiffOptions(new CompositeConfiguration()));
        assertFalse(changedOpenApi.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        ChangedOpenApi changedOpenApi = new ChangedOpenApi(new OpenApiDiffOptions(new CompositeConfiguration()));
        assertFalse(changedOpenApi.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentFields() {
        OpenAPI oldSpec = new OpenAPI();
        OpenAPI newSpec = new OpenAPI();
        List<Endpoint> newEndpoints = Collections.emptyList();
        List<Endpoint> missingEndpoints = Collections.emptyList();
        List<ChangedOperation> changedOperations = Collections.emptyList();
        ChangedExtensions changedExtensions = new ChangedExtensions(Collections.emptyMap(), Collections.emptyMap(), null);

        ChangedOpenApi changedOpenApi1 = new ChangedOpenApi(new OpenApiDiffOptions(new CompositeConfiguration()))
                .setOldSpecOpenApi(oldSpec)
                .setNewSpecOpenApi(newSpec)
                .setNewEndpoints(newEndpoints)
                .setMissingEndpoints(missingEndpoints)
                .setChangedOperations(changedOperations)
                .setChangedExtensions(changedExtensions);

        ChangedOpenApi changedOpenApi2 = new ChangedOpenApi(new OpenApiDiffOptions(new CompositeConfiguration()))
                .setOldSpecOpenApi(oldSpec)
                .setNewSpecOpenApi(newSpec)
                .setNewEndpoints(newEndpoints)
                .setMissingEndpoints(missingEndpoints)
                .setChangedOperations(changedOperations)
                .setChangedExtensions(changedExtensions);

        assertTrue(changedOpenApi1.equals(changedOpenApi2));
    }

    @Test
    public void testEquals_DifferentOldSpecOpenApi() {
        OpenAPI oldSpec1 = new OpenAPI();
        OpenAPI oldSpec2 = new OpenAPI();
        OpenAPI newSpec = new OpenAPI();
        List<Endpoint> newEndpoints = Collections.emptyList();
        List<Endpoint> missingEndpoints = Collections.emptyList();
        List<ChangedOperation> changedOperations = Collections.emptyList();
        ChangedExtensions changedExtensions = new ChangedExtensions(Collections.emptyMap(), Collections.emptyMap(), null);

        ChangedOpenApi changedOpenApi1 = new ChangedOpenApi(new OpenApiDiffOptions(new CompositeConfiguration()))
                .setOldSpecOpenApi(oldSpec1)
                .setNewSpecOpenApi(newSpec)
                .setNewEndpoints(newEndpoints)
                .setMissingEndpoints(missingEndpoints)
                .setChangedOperations(changedOperations)
                .setChangedExtensions(changedExtensions);

        ChangedOpenApi changedOpenApi2 = new ChangedOpenApi(new OpenApiDiffOptions(new CompositeConfiguration()))
                .setOldSpecOpenApi(oldSpec2)
                .setNewSpecOpenApi(newSpec)
                .setNewEndpoints(newEndpoints)
                .setMissingEndpoints(missingEndpoints)
                .setChangedOperations(changedOperations)
                .setChangedExtensions(changedExtensions);

        assertFalse(changedOpenApi1.equals(changedOpenApi2));
    }
}
