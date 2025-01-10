
package org.openapitools.openapidiff.core.output;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import j2html.tags.specialized.OlTag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.openapidiff.core.exception.RendererException;

@ExtendWith(MockitoExtension.class)
public class HtmlRender_renderHtmlTest {

    @Mock
    private OlTag ol_new;

    @Mock
    private OlTag ol_miss;

    @Mock
    private OlTag ol_deprec;

    @Mock
    private OlTag ol_changed;

    @Mock
    private OutputStreamWriter outputStreamWriter;

    @Test
    public void testRenderHtml_Success() throws IOException {
        HtmlRender htmlRender = new HtmlRender();
        htmlRender.renderHtml(ol_new, ol_miss, ol_deprec, ol_changed, outputStreamWriter);

        verify(outputStreamWriter).close();
    }

    @Test
    public void testRenderHtml_IOException() throws IOException {
        doThrow(new IOException()).when(outputStreamWriter).close();

        HtmlRender htmlRender = new HtmlRender();
        try {
            htmlRender.renderHtml(ol_new, ol_miss, ol_deprec, ol_changed, outputStreamWriter);
        } catch (RendererException e) {
            // Expected exception
            return;
        }
        throw new AssertionError("Expected RendererException was not thrown");
    }
}
