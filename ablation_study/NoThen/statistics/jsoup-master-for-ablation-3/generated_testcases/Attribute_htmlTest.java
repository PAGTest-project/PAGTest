
package org.jsoup.nodes;

import org.jsoup.SerializationException;
import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class Attribute_htmlTest {

    @Test
    public void testHtml_SuccessfulEncoding() throws IOException {
        Attribute attr = new Attribute("key", "value");
        StringBuilder mockSb = mock(StringBuilder.class);
        Document mockDoc = mock(Document.class);
        Document.OutputSettings mockOutputSettings = mock(Document.OutputSettings.class);

        when(mockDoc.outputSettings()).thenReturn(mockOutputSettings);
        when(mockOutputSettings.syntax()).thenReturn(Document.OutputSettings.Syntax.html);
        when(StringUtil.borrowBuilder()).thenReturn(mockSb);
        when(StringUtil.releaseBuilder(mockSb)).thenReturn("encodedValue");

        doAnswer(invocation -> {
            Appendable accum = invocation.getArgument(0);
            Document.OutputSettings out = invocation.getArgument(1);
            accum.append("encodedValue");
            return null;
        }).when(attr).html(any(Appendable.class), any(Document.OutputSettings.class));

        String result = attr.html();
        assertEquals("encodedValue", result);
    }

    @Test
    public void testHtml_IOException() throws IOException {
        Attribute attr = new Attribute("key", "value");
        StringBuilder mockSb = mock(StringBuilder.class);
        Document mockDoc = mock(Document.class);
        Document.OutputSettings mockOutputSettings = mock(Document.OutputSettings.class);

        when(mockDoc.outputSettings()).thenReturn(mockOutputSettings);
        when(mockOutputSettings.syntax()).thenReturn(Document.OutputSettings.Syntax.html);
        when(StringUtil.borrowBuilder()).thenReturn(mockSb);

        doThrow(new IOException("Test Exception")).when(attr).html(any(Appendable.class), any(Document.OutputSettings.class));

        assertThrows(SerializationException.class, () -> {
            attr.html();
        });
    }
}
