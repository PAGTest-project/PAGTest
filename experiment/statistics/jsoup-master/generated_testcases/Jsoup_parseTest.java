
package org.jsoup;

import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class Jsoup_parseTest {

    private URL mockUrl;
    private HttpConnection mockConnection;

    @BeforeEach
    public void setUp() throws Exception {
        mockUrl = new URL("http://example.com");
        mockConnection = mock(HttpConnection.class);
        when(HttpConnection.connect(mockUrl)).thenReturn(mockConnection);
    }

    @Test
    public void testParseSuccess() throws IOException {
        Document mockDocument = mock(Document.class);
        when(mockConnection.get()).thenReturn(mockDocument);

        Document result = Jsoup.parse(mockUrl, 5000);
        assertEquals(mockDocument, result);
        verify(mockConnection).timeout(5000);
        verify(mockConnection).get();
    }

    @Test
    public void testParseIOException() throws IOException {
        when(mockConnection.get()).thenThrow(new IOException("Connection error"));

        assertThrows(IOException.class, () -> {
            Jsoup.parse(mockUrl, 5000);
        });
        verify(mockConnection).timeout(5000);
        verify(mockConnection).get();
    }
}
