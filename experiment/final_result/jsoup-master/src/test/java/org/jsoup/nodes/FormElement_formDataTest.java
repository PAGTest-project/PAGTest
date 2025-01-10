
package org.jsoup.nodes;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FormElement_formDataTest {

    private FormElement formElement;

    @BeforeEach
    public void setUp() {
        formElement = new FormElement(org.jsoup.parser.Tag.valueOf("form"), "http://example.com", null);
    }

    @Test
    public void testFormDataWithInputElements() {
        String html = "<form><input name='username' value='testuser'><input name='password' value='testpass'></form>";
        Document doc = Jsoup.parse(html);
        formElement = (FormElement) doc.select("form").first();

        List<Connection.KeyVal> formData = formElement.formData();

        assertEquals(2, formData.size());
        assertEquals("username=testuser", formData.get(0).toString());
        assertEquals("password=testpass", formData.get(1).toString());
    }

    @Test
    public void testFormDataWithSelectElement() {
        String html = "<form><select name='color'><option value='red' selected>Red</option><option value='blue'>Blue</option></select></form>";
        Document doc = Jsoup.parse(html);
        formElement = (FormElement) doc.select("form").first();

        List<Connection.KeyVal> formData = formElement.formData();

        assertEquals(1, formData.size());
        assertEquals("color=red", formData.get(0).toString());
    }

    @Test
    public void testFormDataWithCheckboxElement() {
        String html = "<form><input type='checkbox' name='subscribe' value='yes' checked></form>";
        Document doc = Jsoup.parse(html);
        formElement = (FormElement) doc.select("form").first();

        List<Connection.KeyVal> formData = formElement.formData();

        assertEquals(1, formData.size());
        assertEquals("subscribe=yes", formData.get(0).toString());
    }

    @Test
    public void testFormDataWithDisabledElement() {
        String html = "<form><input name='username' value='testuser' disabled></form>";
        Document doc = Jsoup.parse(html);
        formElement = (FormElement) doc.select("form").first();

        List<Connection.KeyVal> formData = formElement.formData();

        assertEquals(0, formData.size());
    }

    @Test
    public void testFormDataWithButtonElement() {
        String html = "<form><input type='button' name='button' value='Click'></form>";
        Document doc = Jsoup.parse(html);
        formElement = (FormElement) doc.select("form").first();

        List<Connection.KeyVal> formData = formElement.formData();

        assertEquals(0, formData.size());
    }
}
