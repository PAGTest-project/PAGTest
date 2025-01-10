
package org.jsoup.nodes;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.helper.Validate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.jsoup.parser.Tag;

public class FormElement_submitTest {

    private FormElement formElement;
    private Document mockDocument;
    private Connection mockConnection;

    @BeforeEach
    public void setUp() {
        formElement = new FormElement(Tag.valueOf("form"), "http://example.com", null);
        mockDocument = mock(Document.class);
        mockConnection = mock(Connection.class);
        when(mockDocument.connection()).thenReturn(mockConnection);
        when(mockConnection.newRequest()).thenReturn(mockConnection);
    }

    @Test
    public void testSubmitWithAction() {
        formElement.attr("action", "/submit");
        formElement.attr("method", "POST");
        when(formElement.ownerDocument()).thenReturn(mockDocument);

        Connection result = formElement.submit();

        verify(mockConnection).url("http://example.com/submit");
        verify(mockConnection).method(Connection.Method.POST);
        verify(mockConnection).data(formElement.formData());
        assertNotNull(result);
    }

    @Test
    public void testSubmitWithoutAction() {
        formElement.attr("method", "GET");
        when(formElement.ownerDocument()).thenReturn(mockDocument);

        Connection result = formElement.submit();

        verify(mockConnection).url("http://example.com");
        verify(mockConnection).method(Connection.Method.GET);
        verify(mockConnection).data(formElement.formData());
        assertNotNull(result);
    }

    @Test
    public void testSubmitWithInvalidAction() {
        formElement.attr("action", "");
        when(formElement.ownerDocument()).thenReturn(mockDocument);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            formElement.submit();
        });

        assertEquals("Could not determine a form action URL for submit. Ensure you set a base URI when parsing.", exception.getMessage());
    }
}
