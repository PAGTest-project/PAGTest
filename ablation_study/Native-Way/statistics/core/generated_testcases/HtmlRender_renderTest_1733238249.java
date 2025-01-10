
package org.openapitools.openapidiff.core.output;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import org.openapitools.openapidiff.core.model.Endpoint;

import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class HtmlRender_renderTest {

    @Test
    public void testRender_AllPathsCovered() throws Exception {
        // Given
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        List<Endpoint> newEndpoints = Collections.singletonList(new Endpoint());
        List<Endpoint> missingEndpoints = Collections.singletonList(new Endpoint());
        List<Endpoint> deprecatedEndpoints = Collections.singletonList(new Endpoint());
        List<ChangedOperation> changedOperations = Collections.singletonList(new ChangedOperation());

        when(diff.getNewEndpoints()).thenReturn(newEndpoints);
        when(diff.getMissingEndpoints()).thenReturn(missingEndpoints);
        when(diff.getDeprecatedEndpoints()).thenReturn(deprecatedEndpoints);
        when(diff.getChangedOperations()).thenReturn(changedOperations);

        HtmlRender htmlRender = new HtmlRender();

        // When
        htmlRender.render(diff, outputStreamWriter);

        // Then
        verify(diff).getNewEndpoints();
        verify(diff).getMissingEndpoints();
        verify(diff).getDeprecatedEndpoints();
        verify(diff).getChangedOperations();
        verify(outputStreamWriter).close();
    }
}
