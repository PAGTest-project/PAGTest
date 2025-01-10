
package org.openapitools.openapidiff.core.output;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.Endpoint;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import org.openapitools.openapidiff.core.exception.RendererException;

import j2html.tags.specialized.OlTag;

public class HtmlRender_renderHtmlTest {

    private HtmlRender htmlRender;
    private OlTag ol_new;
    private OlTag ol_miss;
    private OlTag ol_deprec;
    private OlTag ol_changed;
    private OutputStreamWriter outputStreamWriter;

    @BeforeEach
    public void setUp() {
        htmlRender = new HtmlRender();
        ol_new = mock(OlTag.class);
        ol_miss = mock(OlTag.class);
        ol_deprec = mock(OlTag.class);
        ol_changed = mock(OlTag.class);
        outputStreamWriter = new OutputStreamWriter(new ByteArrayOutputStream());
    }

    @Test
    public void testRenderHtmlSuccess() {
        assertDoesNotThrow(() -> htmlRender.renderHtml(ol_new, ol_miss, ol_deprec, ol_changed, outputStreamWriter));
    }

    @Test
    public void testRenderHtmlIOException() throws IOException {
        OutputStreamWriter mockOutputStreamWriter = mock(OutputStreamWriter.class);
        when(mockOutputStreamWriter.append("")).thenThrow(new IOException());
        assertThrows(RendererException.class, () -> htmlRender.renderHtml(ol_new, ol_miss, ol_deprec, ol_changed, mockOutputStreamWriter));
    }

    @Test
    public void testRenderHtmlWithEndpoints() {
        List<Endpoint> newEndpoints = Arrays.asList(new Endpoint());
        List<Endpoint> missingEndpoints = Arrays.asList(new Endpoint());
        List<Endpoint> deprecatedEndpoints = Arrays.asList(new Endpoint());
        List<ChangedOperation> changedOperations = Arrays.asList(new ChangedOperation());

        OlTag ol_newEndpoint = htmlRender.ol_newEndpoint(newEndpoints);
        OlTag ol_missingEndpoint = htmlRender.ol_missingEndpoint(missingEndpoints);
        OlTag ol_deprecatedEndpoint = htmlRender.ol_deprecatedEndpoint(deprecatedEndpoints);
        OlTag ol_changedOperations = htmlRender.ol_changed(changedOperations);

        assertDoesNotThrow(() -> htmlRender.renderHtml(ol_newEndpoint, ol_missingEndpoint, ol_deprecatedEndpoint, ol_changedOperations, outputStreamWriter));
    }
}
