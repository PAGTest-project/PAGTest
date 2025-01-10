
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
        Path path = mock(Path.class);
        Charset charset = Charset.forName("UTF-8");
        String baseUri = "http://example.com";
        Parser parser = mock(Parser.class);

        DataUtil.CharsetDoc charsetDoc = mock(DataUtil.CharsetDoc.class);
        when(charsetDoc.input).thenReturn(mock(ControllableInputStream.class));
        when(charsetDoc.charset).thenReturn(charset);

        BufferedReader reader = mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("test");

        StreamParser streamer = mock(StreamParser.class);
        doNothing().when(streamer).parse(any(BufferedReader.class), eq(baseUri));

        // When
        StreamParser result = DataUtil.streamParser(path, charset, baseUri, parser);

        // Then
        assertNotNull(result);
        verify(streamer).parse(any(BufferedReader.class), eq(baseUri));
    }
}
