
package org.jsoup.nodes;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormElement_formDataTest {

    private FormElement formElement;

    @BeforeEach
    public void setUp() {
        String html = "<form><input name='q' value='test'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        formElement = ((FormElement) doc.select("form").first());
    }

    @Test
    public void testFormDataWithSingleInput() {
        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("q", data.get(0).key());
        assertEquals("test", data.get(0).value());
    }

    @Test
    public void testFormDataWithDisabledInput() {
        String html = "<form><input name='q' value='test' disabled></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(0, data.size());
    }

    @Test
    public void testFormDataWithSelect() {
        String html = "<form><select name='choice'><option value='1' selected>One</option><option value='2'>Two</option></select></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("choice", data.get(0).key());
        assertEquals("1", data.get(0).value());
    }

    @Test
    public void testFormDataWithCheckbox() {
        String html = "<form><input type='checkbox' name='agree' value='yes' checked></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("agree", data.get(0).key());
        assertEquals("yes", data.get(0).value());
    }

    @Test
    public void testFormDataWithRadio() {
        String html = "<form><input type='radio' name='choice' value='yes' checked></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("choice", data.get(0).key());
        assertEquals("yes", data.get(0).value());
    }

    @Test
    public void testFormDataWithButton() {
        String html = "<form><input type='button' name='btn' value='Click'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(0, data.size());
    }
}
