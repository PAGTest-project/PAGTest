
package org.jsoup.nodes;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.helper.Validate;
import org.jsoup.internal.SharedConstants;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.QueryParser;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FormElement_submitTest {

    private FormElement formElement;

    @BeforeEach
    public void setUp() {
        formElement = new FormElement(Tag.valueOf("form"), "http://example.com", null);
    }

    @Test
    public void testSubmitWithActionAttribute() {
        formElement.attr("action", "http://example.com/submit");
        formElement.attr("method", "POST");

        Connection connection = formElement.submit();
        assertEquals("http://example.com/submit", connection.request().url().toString());
        assertEquals(Connection.Method.POST, connection.request().method());
    }

    @Test
    public void testSubmitWithoutActionAttribute() {
        formElement.attr("method", "GET");

        Connection connection = formElement.submit();
        assertEquals("http://example.com", connection.request().url().toString());
        assertEquals(Connection.Method.GET, connection.request().method());
    }

    @Test
    public void testSubmitWithOwnerDocument() {
        Document owner = new Document("http://example.com");
        formElement.attr("action", "http://example.com/submit");
        formElement.attr("method", "POST");
        formElement.ownerDocument = () -> owner;

        Connection connection = formElement.submit();
        assertEquals("http://example.com/submit", connection.request().url().toString());
        assertEquals(Connection.Method.POST, connection.request().method());
    }

    @Test
    public void testSubmitWithoutOwnerDocument() {
        formElement.attr("action", "http://example.com/submit");
        formElement.attr("method", "POST");

        Connection connection = formElement.submit();
        assertEquals("http://example.com/submit", connection.request().url().toString());
        assertEquals(Connection.Method.POST, connection.request().method());
    }

    @Test
    public void testSubmitWithEmptyAction() {
        formElement.attr("action", "");
        formElement.attr("method", "POST");

        assertThrows(IllegalArgumentException.class, () -> formElement.submit());
    }

    @Test
    public void testSubmitWithInvalidMethod() {
        formElement.attr("action", "http://example.com/submit");
        formElement.attr("method", "INVALID");

        Connection connection = formElement.submit();
        assertEquals("http://example.com/submit", connection.request().url().toString());
        assertEquals(Connection.Method.GET, connection.request().method());
    }
}
