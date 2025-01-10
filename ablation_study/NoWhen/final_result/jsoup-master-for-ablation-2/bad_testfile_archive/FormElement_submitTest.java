
package org.jsoup.nodes;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.integration.TestServer;
import org.jsoup.integration.servlets.CookieServlet;
import org.jsoup.integration.servlets.EchoServlet;
import org.jsoup.integration.servlets.FileServlet;
import org.jsoup.select.Elements;
import org.jsoup.select.SelectorTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FormElement_submitTest {

    private static TestServer server;

    @BeforeAll
    public static void setUp() throws IOException {
        server = new TestServer();
        server.before();
    }

    @Test
    public void testSubmitWithGetMethod() {
        Document doc = Jsoup.parse("<form action='http://example.com/search' method='get'><input name='q' value='search'></form>");
        FormElement form = (FormElement) doc.select("form").first();

        Connection connection = form.submit();
        assertEquals("http://example.com/search", connection.request().url().toString());
        assertEquals(Connection.Method.GET, connection.request().method());
    }

    @Test
    public void testSubmitWithPostMethod() {
        Document doc = Jsoup.parse("<form action='http://example.com/search' method='post'><input name='q' value='search'></form>");
        FormElement form = (FormElement) doc.select("form").first();

        Connection connection = form.submit();
        assertEquals("http://example.com/search", connection.request().url().toString());
        assertEquals(Connection.Method.POST, connection.request().method());
    }

    @Test
    public void testSubmitWithDefaultMethod() {
        Document doc = Jsoup.parse("<form action='http://example.com/search'><input name='q' value='search'></form>");
        FormElement form = (FormElement) doc.select("form").first();

        Connection connection = form.submit();
        assertEquals("http://example.com/search", connection.request().url().toString());
        assertEquals(Connection.Method.GET, connection.request().method());
    }

    @Test
    public void testSubmitWithNoAction() {
        Document doc = Jsoup.parse("<form method='post'><input name='q' value='search'></form>");
        FormElement form = (FormElement) doc.select("form").first();

        assertThrows(IllegalArgumentException.class, () -> form.submit());
    }
}
