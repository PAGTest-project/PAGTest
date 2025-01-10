
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.swagger.v3.oas.models.OpenAPI;
import java.util.Collections;
import java.util.List;

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
        changedOpenApi = new ChangedOpenApi(new OpenApiDiffOptions());
        oldSpecOpenApi = new OpenAPI();
        newSpecOpenApi = new OpenAPI();
        newEndpoints = Collections.emptyList();
        missingEndpoints = Collections.emptyList();
        changedOperations = Collections.emptyList();
        changedExtensions = new ChangedExtensions();

        changedOpenApi.setOldSpecOpenApi(oldSpecOpenApi)
                      .setNewSpecOpenApi(newSpecOpenApi)
                      .setNewEndpoints(newEndpoints)
                      .setMissingEndpoints(missingEndpoints)
                      .setChangedOperations(changedOperations)
                      .setChangedExtensions(changedExtensions);
    }

    @Test
    public void testToString() {
        String expected = "ChangedOpenApi(oldSpecOpenApi=" + oldSpecOpenApi
                + ", newSpecOpenApi=" + newSpecOpenApi
                + ", newEndpoints=" + newEndpoints
                + ", missingEndpoints=" + missingEndpoints
                + ", changedOperations=" + changedOperations
                + ", changedExtensions=" + changedExtensions
                + ")";
        assertEquals(expected, changedOpenApi.toString());
    }

    @Test
    public void testToStringWithNullValues() {
        changedOpenApi.setOldSpecOpenApi(null)
                      .setNewSpecOpenApi(null)
                      .setNewEndpoints(null)
                      .setMissingEndpoints(null)
                      .setChangedOperations(null)
                      .setChangedExtensions(null);

        String expected = "ChangedOpenApi(oldSpecOpenApi=null"
                + ", newSpecOpenApi=null"
                + ", newEndpoints=null"
                + ", missingEndpoints=null"
                + ", changedOperations=null"
                + ", changedExtensions=null"
                + ")";
        assertEquals(expected, changedOpenApi.toString());
    }

    @Test
    public void testToStringWithNonEmptyValues() {
        newEndpoints = Collections.singletonList(new Endpoint());
        missingEndpoints = Collections.singletonList(new Endpoint());
        changedOperations = Collections.singletonList(new ChangedOperation());
        changedExtensions = new ChangedExtensions();

        changedOpenApi.setNewEndpoints(newEndpoints)
                      .setMissingEndpoints(missingEndpoints)
                      .setChangedOperations(changedOperations)
                      .setChangedExtensions(changedExtensions);

        String expected = "ChangedOpenApi(oldSpecOpenApi=" + oldSpecOpenApi
                + ", newSpecOpenApi=" + newSpecOpenApi
                + ", newEndpoints=" + newEndpoints
                + ", missingEndpoints=" + missingEndpoints
                + ", changedOperations=" + changedOperations
                + ", changedExtensions=" + changedExtensions
                + ")";
        assertEquals(expected, changedOpenApi.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        ChangedOpenApi changedOpenApi2 = new ChangedOpenApi(new OpenApiDiffOptions());
        changedOpenApi2.setOldSpecOpenApi(oldSpecOpenApi)
                       .setNewSpecOpenApi(newSpecOpenApi)
                       .setNewEndpoints(newEndpoints)
                       .setMissingEndpoints(missingEndpoints)
                       .setChangedOperations(changedOperations)
                       .setChangedExtensions(changedExtensions);

        assertTrue(changedOpenApi.equals(changedOpenApi2));
        assertEquals(changedOpenApi.hashCode(), changedOpenApi2.hashCode());
    }
}
