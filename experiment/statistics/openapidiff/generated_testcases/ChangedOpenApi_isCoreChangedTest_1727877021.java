
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.BackwardIncompatibleProp.OPENAPI_ENDPOINTS_DECREASED;

public class ChangedOpenApi_isCoreChangedTest {

    private ChangedOpenApi changedOpenApi;
    private OpenApiDiffOptions options;

    @BeforeEach
    public void setUp() {
        options = mock(OpenApiDiffOptions.class);
        changedOpenApi = new ChangedOpenApi(options);
    }

    @Test
    public void testIsCoreChangedNoChanges() {
        changedOpenApi.setNewEndpoints(Collections.emptyList());
        changedOpenApi.setMissingEndpoints(Collections.emptyList());
        assertEquals(DiffResult.NO_CHANGES, changedOpenApi.isCoreChanged());
    }

    @Test
    public void testIsCoreChangedCompatible() {
        changedOpenApi.setNewEndpoints(List.of(new Endpoint()));
        changedOpenApi.setMissingEndpoints(Collections.emptyList());
        assertEquals(DiffResult.COMPATIBLE, changedOpenApi.isCoreChanged());
    }

    @Test
    public void testIsCoreChangedIncompatible() {
        when(options.getConfig()).thenReturn(mock(CompositeConfiguration.class));
        when(OPENAPI_ENDPOINTS_DECREASED.enabled(options.getConfig())).thenReturn(true);

        changedOpenApi.setNewEndpoints(Collections.emptyList());
        changedOpenApi.setMissingEndpoints(List.of(new Endpoint()));
        assertEquals(DiffResult.INCOMPATIBLE, changedOpenApi.isCoreChanged());
    }
}
