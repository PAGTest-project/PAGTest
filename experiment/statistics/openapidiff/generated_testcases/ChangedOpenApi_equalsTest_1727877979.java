
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedOpenApi_equalsTest {

    private ChangedOpenApi changedOpenApi;
    private OpenAPI oldSpecOpenApi;
    private OpenAPI newSpecOpenApi;

    @BeforeEach
    public void setUp() {
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        changedOpenApi = new ChangedOpenApi(options);
        oldSpecOpenApi = new OpenAPI();
        newSpecOpenApi = new OpenAPI();
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
        changedOpenApi.setOldSpecOpenApi(oldSpecOpenApi);
        ChangedOpenApi other = new ChangedOpenApi(new OpenApiDiffOptions());
        other.setOldSpecOpenApi(new OpenAPI());
        assertFalse(changedOpenApi.equals(other));
    }

    @Test
    public void testEquals_DifferentNewSpecOpenApi() {
        changedOpenApi.setNewSpecOpenApi(newSpecOpenApi);
        ChangedOpenApi other = new ChangedOpenApi(new OpenApiDiffOptions());
        other.setNewSpecOpenApi(new OpenAPI());
        assertFalse(changedOpenApi.equals(other));
    }

    @Test
    public void testEquals_SameSpecs() {
        changedOpenApi.setOldSpecOpenApi(oldSpecOpenApi);
        changedOpenApi.setNewSpecOpenApi(newSpecOpenApi);
        ChangedOpenApi other = new ChangedOpenApi(new OpenApiDiffOptions());
        other.setOldSpecOpenApi(oldSpecOpenApi);
        other.setNewSpecOpenApi(newSpecOpenApi);
        assertTrue(changedOpenApi.equals(other));
    }
}
