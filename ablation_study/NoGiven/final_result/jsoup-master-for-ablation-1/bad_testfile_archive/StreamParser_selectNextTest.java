
package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StreamParser_selectNextTest {

    @Test
    public void testSelectNext_ElementFound() throws IOException {
        // Given
        StreamParser parser = new StreamParser(Parser.htmlParser());
        parser.parse(new StringReader("<html><body><div class='test'></div></body></html>"), "http://example.com");
        Evaluator eval = Mockito.mock(Evaluator.class);
        Document doc = parser.document();
        when(eval.asPredicate(doc)).thenReturn(element -> "test".equals(element.className()));

        // When
        Element result = parser.selectNext(eval);

        // Then
        assertNotNull(result);
        assertEquals("test", result.className());
    }

    @Test
    public void testSelectNext_NoElementFound() throws IOException {
        // Given
        StreamParser parser = new StreamParser(Parser.htmlParser());
        parser.parse(new StringReader("<html><body><div class='not-test'></div></body></html>"), "http://example.com");
        Evaluator eval = Mockito.mock(Evaluator.class);
        Document doc = parser.document();
        when(eval.asPredicate(doc)).thenReturn(element -> "test".equals(element.className()));

        // When
        Element result = parser.selectNext(eval);

        // Then
        assertNull(result);
    }

    @Test
    public void testSelectNext_IOException() throws IOException {
        // Given
        StreamParser parser = new StreamParser(Parser.htmlParser());
        parser.parse(new StringReader("<html><body><div class='test'></div></body></html>"), "http://example.com");
        Evaluator eval = Mockito.mock(Evaluator.class);
        Document doc = parser.document();
        when(eval.asPredicate(doc)).thenThrow(new UncheckedIOException(new IOException("Test IO Exception")));

        // When & Then
        IOException exception = assertThrows(IOException.class, () -> parser.selectNext(eval));
        assertEquals("Test IO Exception", exception.getMessage());
    }
}
