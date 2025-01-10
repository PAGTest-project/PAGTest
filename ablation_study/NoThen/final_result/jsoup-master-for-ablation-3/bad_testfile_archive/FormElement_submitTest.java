
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
    public void testSubmitWithAction() throws IOException {
        final String html = "<html><body><form action='/submit' method='post'><input name='foo' value='bar'></form></body></html>";
        final Document doc = Jsoup.parse(html, "http://example.com");
        final FormElement form = (FormElement) doc.selectFirst("form");

        Connection connection = form.submit();
        assertEquals("http://example.com/submit", connection.request().url().toString());
        assertEquals(Connection.Method.POST, connection.request().method());
    }

    @Test
    public void testSubmitWithoutAction() throws IOException {
        final String html = "<html><body><form method='get'><input name='foo' value='bar'></form></body></html>";
        final Document doc = Jsoup.parse(html, "http://example.com");
        final FormElement form = (FormElement) doc.selectFirst("form");

        Connection connection = form.submit();
        assertEquals("http://example.com/", connection.request().url().toString());
        assertEquals(Connection.Method.GET, connection.request().method());
    }

    @Test
    public void testSubmitWithInvalidAction() {
        final String html = "<html><body><form action=''><input name='foo' value='bar'></form></body></html>";
        final Document doc = Jsoup.parse(html, "http://example.com");
        final FormElement form = (FormElement) doc.selectFirst("form");

        assertThrows(IllegalArgumentException.class, form::submit);
    }

    @Test
    public void testFormData() {
        final String html = "<html><body><form><input name='foo' value='bar'><input type='checkbox' name='check' value='1' checked></form></body></html>";
        final Document doc = Jsoup.parse(html, "http://example.com");
        final FormElement form = (FormElement) doc.selectFirst("form");

        List<Connection.KeyVal> keyVals = form.formData();
        assertEquals(2, keyVals.size());
        assertEquals("foo", keyVals.get(0).key());
        assertEquals("bar", keyVals.get(0).value());
        assertEquals("check", keyVals.get(1).key());
        assertEquals("1", keyVals.get(1).value());
    }
}
