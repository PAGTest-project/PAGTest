
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.OpenAPI;
import java.util.Collections;
import java.util.List;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedOpenApi_equalsTest {

    private ChangedOpenApi changedOpenApi;
    private OpenApiDiffOptions options;

    @BeforeEach
    public void setUp() {
        CompositeConfiguration config = new CompositeConfiguration();
        options = OpenApiDiffOptions.fromConfig(config);
        changedOpenApi = new ChangedOpenApi(options);
    }

    @Test
    public void testEquals_SameInstance() {
        assertTrue(changedOpenApi.equals(changedOpenApi));
    }

    @Test
    public void testEquals_NullObject() {
        assertFalse(changedOpenApi.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        assertFalse(changedOpenApi.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentOldSpecOpenApi() {
        OpenAPI oldSpecOpenApi1 = new OpenAPI();
        OpenAPI oldSpecOpenApi2 = new OpenAPI();
        changedOpenApi.setOldSpecOpenApi(oldSpecOpenApi1);
        ChangedOpenApi other = new ChangedOpenApi(options).setOldSpecOpenApi(oldSpecOpenApi2);
        assertFalse(changedOpenApi.equals(other));
    }

    @Test
    public void testEquals_DifferentNewSpecOpenApi() {
        OpenAPI newSpecOpenApi1 = new OpenAPI();
        OpenAPI newSpecOpenApi2 = new OpenAPI();
        changedOpenApi.setNewSpecOpenApi(newSpecOpenApi1);
        ChangedOpenApi other = new ChangedOpenApi(options).setNewSpecOpenApi(newSpecOpenApi2);
        assertFalse(changedOpenApi.equals(other));
    }

    @Test
    public void testEquals_DifferentNewEndpoints() {
        List<Endpoint> newEndpoints1 = Collections.singletonList(new Endpoint());
        List<Endpoint> newEndpoints2 = Collections.singletonList(new Endpoint());
        changedOpenApi.setNewEndpoints(newEndpoints1);
        ChangedOpenApi other = new ChangedOpenApi(options).setNewEndpoints(newEndpoints2);
        assertFalse(changedOpenApi.equals(other));
    }

    @Test
    public void testEquals_DifferentMissingEndpoints() {
        List<Endpoint> missingEndpoints1 = Collections.singletonList(new Endpoint());
        List<Endpoint> missingEndpoints2 = Collections.singletonList(new Endpoint());
        changedOpenApi.setMissingEndpoints(missingEndpoints1);
        ChangedOpenApi other = new ChangedOpenApi(options).setMissingEndpoints(missingEndpoints2);
        assertFalse(changedOpenApi.equals(other));
    }

    @Test
    public void testEquals_DifferentChangedOperations() {
        List<ChangedOperation> changedOperations1 = Collections.singletonList(new ChangedOperation("path", null, new io.swagger.v3.oas.models.Operation(), new io.swagger.v3.oas.models.Operation()));
        List<ChangedOperation> changedOperations2 = Collections.singletonList(new ChangedOperation("path", null, new io.swagger.v3.oas.models.Operation(), new io.swagger.v3.oas.models.Operation()));
        changedOpenApi.setChangedOperations(changedOperations1);
        ChangedOpenApi other = new ChangedOpenApi(options).setChangedOperations(changedOperations2);
        assertFalse(changedOpenApi.equals(other));
    }

    @Test
    public void testEquals_DifferentChangedExtensions() {
        ChangedExtensions changedExtensions1 = new ChangedExtensions(null, null, null);
        ChangedExtensions changedExtensions2 = new ChangedExtensions(null, null, null);
        changedOpenApi.setChangedExtensions(changedExtensions1);
        ChangedOpenApi other = new ChangedOpenApi(options).setChangedExtensions(changedExtensions2);
        assertFalse(changedOpenApi.equals(other));
    }

    @Test
    public void testEquals_SameFields() {
        OpenAPI oldSpecOpenApi = new OpenAPI();
        OpenAPI newSpecOpenApi = new OpenAPI();
        List<Endpoint> newEndpoints = Collections.singletonList(new Endpoint());
        List<Endpoint> missingEndpoints = Collections.singletonList(new Endpoint());
        List<ChangedOperation> changedOperations = Collections.singletonList(new ChangedOperation("path", null, new io.swagger.v3.oas.models.Operation(), new io.swagger.v3.oas.models.Operation()));
        ChangedExtensions changedExtensions = new ChangedExtensions(null, null, null);

        changedOpenApi.setOldSpecOpenApi(oldSpecOpenApi)
                      .setNewSpecOpenApi(newSpecOpenApi)
                      .setNewEndpoints(newEndpoints)
                      .setMissingEndpoints(missingEndpoints)
                      .setChangedOperations(changedOperations)
                      .setChangedExtensions(changedExtensions);

        ChangedOpenApi other = new ChangedOpenApi(options)
                                .setOldSpecOpenApi(oldSpecOpenApi)
                                .setNewSpecOpenApi(newSpecOpenApi)
                                .setNewEndpoints(newEndpoints)
                                .setMissingEndpoints(missingEndpoints)
                                .setChangedOperations(changedOperations)
                                .setChangedExtensions(changedExtensions);

        assertTrue(changedOpenApi.equals(other));
    }
}
