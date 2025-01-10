
package org.jsoup.helper;

import org.jsoup.parser.Parser;
import org.jsoup.parser.StreamParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;

import static org.mockito.Mockito.*;

public class DataUtil_streamParserTest {

    private Path mockPath;
    private Charset mockCharset;
    private String mockBaseUri;
    private Parser mockParser;
    private DataUtil.CharsetDoc mockCharsetDoc;
    private BufferedReader mockReader;

    @BeforeEach
    public void setUp() {
        mockPath = mock(Path.class);
        mockCharset = mock(Charset.class);
        mockBaseUri = "http://example.com";
        mockParser = mock(Parser.class);
        mockCharsetDoc = mock(DataUtil.CharsetDoc.class);
        mockReader = mock(BufferedReader.class);
    }

    @AfterEach
    public void tearDown() {
        reset(mockPath, mockCharset, mockParser, mockCharsetDoc, mockReader);
    }

    @Test
    public void testStreamParser_WithCharset() throws IOException {
        when(mockCharset.name()).thenReturn("UTF-8");
        when(DataUtil.detectCharset(any(), eq("UTF-8"), eq(mockBaseUri), eq(mockParser))).thenReturn(mockCharsetDoc);
        when(mockCharsetDoc.input).thenReturn(mock(ControllableInputStream.class));
        when(mockCharsetDoc.charset).thenReturn(mockCharset);
        when(mockReader.readLine()).thenReturn(null);

        StreamParser result = DataUtil.streamParser(mockPath, mockCharset, mockBaseUri, mockParser);

        Assertions.assertNotNull(result);
        verify(mockCharset, times(1)).name();
        verify(DataUtil.detectCharset(any(), eq("UTF-8"), eq(mockBaseUri), eq(mockParser)), times(1));
        verify(mockReader, times(1)).readLine();
    }

    @Test
    public void testStreamParser_WithoutCharset() throws IOException {
        when(DataUtil.detectCharset(any(), eq(null), eq(mockBaseUri), eq(mockParser))).thenReturn(mockCharsetDoc);
        when(mockCharsetDoc.input).thenReturn(mock(ControllableInputStream.class));
        when(mockCharsetDoc.charset).thenReturn(Charset.forName("UTF-8"));
        when(mockReader.readLine()).thenReturn(null);

        StreamParser result = DataUtil.streamParser(mockPath, null, mockBaseUri, mockParser);

        Assertions.assertNotNull(result);
        verify(DataUtil.detectCharset(any(), eq(null), eq(mockBaseUri), eq(mockParser)), times(1));
        verify(mockReader, times(1)).readLine();
    }
}
