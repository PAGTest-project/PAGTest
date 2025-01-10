
package org.jsoup.helper;

import org.jsoup.parser.Parser;
import org.jsoup.parser.StreamParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class DataUtil_streamParserTest {

    @Test
    public void testStreamParser() throws IOException {
        // Given
        Path mockPath = mock(Path.class);
        Charset mockCharset = mock(Charset.class);
        String baseUri = "http://example.com";
        Parser mockParser = mock(Parser.class);
        DataUtil.CharsetDoc mockCharsetDoc = mock(DataUtil.CharsetDoc.class);
        BufferedReader mockReader = mock(BufferedReader.class);
        StreamParser mockStreamParser = mock(StreamParser.class);

        when(mockCharset.name()).thenReturn("UTF-8");
        when(DataUtil.detectCharset(any(ControllableInputStream.class), anyString(), anyString(), any(Parser.class))).thenReturn(mockCharsetDoc);
        when(mockCharsetDoc.input).thenReturn(mock(ControllableInputStream.class));
        when(mockCharsetDoc.charset).thenReturn(mockCharset);
        when(mockReader.readLine()).thenReturn("test data");
        when(mockStreamParser.parse(any(BufferedReader.class), anyString())).thenReturn(mockStreamParser);

        // When
        StreamParser result = DataUtil.streamParser(mockPath, mockCharset, baseUri, mockParser);

        // Then
        assertNotNull(result);
        verify(mockStreamParser).parse(any(BufferedReader.class), eq(baseUri));
    }
}
