
package org.openapitools.openapidiff.core.output;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.Endpoint;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import j2html.tags.specialized.OlTag;

import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class HtmlRender_renderTest {

    @Test
    public void testRender() throws Exception {
        // Given
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        when(diff.getNewEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getMissingEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getDeprecatedEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getChangedOperations()).thenReturn(Collections.emptyList());

        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

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
