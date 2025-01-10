package org.openapitools.openapidiff.core.output;

import static org.mockito.Mockito.*;

import j2html.tags.specialized.OlTag;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.openapidiff.core.exception.RendererException;

public class HtmlRender_renderHtmlTest {

  @Mock private OlTag ol_new;

  @Mock private OlTag ol_miss;

  @Mock private OlTag ol_deprec;

  @Mock private OlTag ol_changed;

  @Mock private OutputStreamWriter outputStreamWriter;

  private HtmlRender htmlRender;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    htmlRender = new HtmlRender("Test Title", "http://test.css");
  }

  @Test
  public void testRenderHtml_Success() throws IOException {
    htmlRender.renderHtml(ol_new, ol_miss, ol_deprec, ol_changed, outputStreamWriter);

    verify(outputStreamWriter).close();
  }

  @Test
  public void testRenderHtml_IOException() throws IOException {
    doThrow(new IOException("Test Exception")).when(outputStreamWriter).close();

    try {
      htmlRender.renderHtml(ol_new, ol_miss, ol_deprec, ol_changed, outputStreamWriter);
    } catch (RendererException e) {
      assert (e.getMessage().equals("Problem rendering html document."));
    }
  }
}
