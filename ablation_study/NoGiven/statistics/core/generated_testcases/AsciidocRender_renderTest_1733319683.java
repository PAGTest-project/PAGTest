
package org.openapitools.openapidiff.core.output;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.Endpoint;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class AsciidocRender_renderTest {

    @Test
    public void testRenderUnchanged() throws Exception {
        // Given
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        when(diff.isUnchanged()).thenReturn(true);
        when(diff.getNewSpecOpenApi()).thenReturn(mock(OpenAPI.class));
        when(diff.getNewSpecOpenApi().getInfo()).thenReturn(mock(Info.class));
        when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
        when(diff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");

        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        AsciidocRender asciidocRender = new AsciidocRender();

        // When
        asciidocRender.render(diff, outputStreamWriter);

        // Then
        verify(outputStreamWriter, times(4)).write(anyString());
        verify(outputStreamWriter).close();
    }

    @Test
    public void testRenderChanged() throws Exception {
        // Given
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        when(diff.isUnchanged()).thenReturn(false);
        when(diff.isCompatible()).thenReturn(true);
        when(diff.getNewSpecOpenApi()).thenReturn(mock(OpenAPI.class));
        when(diff.getNewSpecOpenApi().getInfo()).thenReturn(mock(Info.class));
        when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
        when(diff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");

        List<Endpoint> newEndpoints = Collections.emptyList();
        List<Endpoint> missingEndpoints = Collections.emptyList();
        List<Endpoint> deprecatedEndpoints = Collections.emptyList();
        List<ChangedOperation> changedOperations = Collections.emptyList();

        when(diff.getNewEndpoints()).thenReturn(newEndpoints);
        when(diff.getMissingEndpoints()).thenReturn(missingEndpoints);
        when(diff.getDeprecatedEndpoints()).thenReturn(deprecatedEndpoints);
        when(diff.getChangedOperations()).thenReturn(changedOperations);

        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        AsciidocRender asciidocRender = new AsciidocRender();

        // When
        asciidocRender.render(diff, outputStreamWriter);

        // Then
        verify(outputStreamWriter, times(6)).write(anyString());
        verify(outputStreamWriter).close();
    }
}
