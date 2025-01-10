
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.BackwardIncompatibleProp;
import org.apache.commons.configuration2.CompositeConfiguration;

public class ChangedOpenApi_isCoreChangedTest {

    private ChangedOpenApi changedOpenApi;
    private OpenApiDiffOptions options;

    @BeforeEach
    public void setUp() {
        options = mock(OpenApiDiffOptions.class);
        changedOpenApi = new ChangedOpenApi(options);
    }

    @Test
    public void testIsCoreChanged_NoChanges() {
        changedOpenApi.setNewEndpoints(Collections.emptyList());
        changedOpenApi.setMissingEndpoints(Collections.emptyList());

        DiffResult result = changedOpenApi.isCoreChanged();

        assertEquals(DiffResult.NO_CHANGES, result);
    }

    @Test
    public void testIsCoreChanged_Compatible() {
        changedOpenApi.setNewEndpoints(List.of(mock(Endpoint.class)));
        changedOpenApi.setMissingEndpoints(Collections.emptyList());

        DiffResult result = changedOpenApi.isCoreChanged();

        assertEquals(DiffResult.COMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible() {
        CompositeConfiguration config = mock(CompositeConfiguration.class);
        when(options.getConfig()).thenReturn(config);
        when(BackwardIncompatibleProp.OPENAPI_ENDPOINTS_DECREASED.enabled(config)).thenReturn(true);

        changedOpenApi.setNewEndpoints(Collections.emptyList());
        changedOpenApi.setMissingEndpoints(List.of(mock(Endpoint.class)));

        DiffResult result = changedOpenApi.isCoreChanged();

        assertEquals(DiffResult.INCOMPATIBLE, result);
    }
}
