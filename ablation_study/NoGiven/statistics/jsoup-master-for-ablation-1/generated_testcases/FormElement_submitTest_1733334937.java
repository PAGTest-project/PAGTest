
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

    private FormElement form;

    @BeforeAll
    public static void setUp() {
        String html = "<form action='http://example.com' method='post'><input name='one' value='two'></form>";
        Document doc = Jsoup.parse(html);
        form = (FormElement) doc.select("form").first();
    }

    @Test
    public void testSubmitWithAction() {
        Connection connection = form.submit();
        assertEquals("http://example.com", connection.request().url().toString());
        assertEquals(Connection.Method.POST, connection.request().method());
    }

    @Test
    public void testSubmitWithoutAction() {
        form.attr("action", "");
        Connection connection = form.submit();
        assertEquals(form.baseUri(), connection.request().url().toString());
        assertEquals(Connection.Method.GET, connection.request().method());
    }

    @Test
    public void testSubmitWithGetMethod() {
        form.attr("method", "GET");
        Connection connection = form.submit();
        assertEquals(Connection.Method.GET, connection.request().method());
    }

    @Test
    public void testSubmitWithFormData() {
        String html = "<form action='http://example.com' method='post'><input name='one' value='two'></form>";
        Document doc = Jsoup.parse(html);
        form = (FormElement) doc.select("form").first();
        Connection connection = form.submit();
        List<Connection.KeyVal> data = connection.request().data();
        assertEquals(1, data.size());
        assertEquals("one=two", data.get(0).toString());
    }
}
