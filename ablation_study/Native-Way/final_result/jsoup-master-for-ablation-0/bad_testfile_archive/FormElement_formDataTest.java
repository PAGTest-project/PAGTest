
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
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html);
        formElement = ((FormElement) doc.select("form").first());
    }

    @Test
    public void testFormDataWithNoElements() {
        List<Connection.KeyVal> data = formElement.formData();
        assertTrue(data.isEmpty());
    }

    @Test
    public void testFormDataWithTextInput() {
        String html = "<form><input type='text' name='username' value='testuser'></form>";
        Document doc = Jsoup.parse(html);
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("username", data.get(0).key());
        assertEquals("testuser", data.get(0).value());
    }

    @Test
    public void testFormDataWithCheckboxChecked() {
        String html = "<form><input type='checkbox' name='subscribe' value='yes' checked></form>";
        Document doc = Jsoup.parse(html);
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("subscribe", data.get(0).key());
        assertEquals("yes", data.get(0).value());
    }

    @Test
    public void testFormDataWithCheckboxUnchecked() {
        String html = "<form><input type='checkbox' name='subscribe' value='yes'></form>";
        Document doc = Jsoup.parse(html);
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertTrue(data.isEmpty());
    }

    @Test
    public void testFormDataWithRadioChecked() {
        String html = "<form><input type='radio' name='gender' value='male' checked></form>";
        Document doc = Jsoup.parse(html);
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("gender", data.get(0).key());
        assertEquals("male", data.get(0).value());
    }

    @Test
    public void testFormDataWithRadioUnchecked() {
        String html = "<form><input type='radio' name='gender' value='male'></form>";
        Document doc = Jsoup.parse(html);
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertTrue(data.isEmpty());
    }

    @Test
    public void testFormDataWithSelect() {
        String html = "<form><select name='color'><option value='red' selected>Red</option></select></form>";
        Document doc = Jsoup.parse(html);
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("color", data.get(0).key());
        assertEquals("red", data.get(0).value());
    }

    @Test
    public void testFormDataWithSelectNoSelected() {
        String html = "<form><select name='color'><option value='red'>Red</option></select></form>";
        Document doc = Jsoup.parse(html);
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("color", data.get(0).key());
        assertEquals("red", data.get(0).value());
    }

    @Test
    public void testFormDataWithDisabledInput() {
        String html = "<form><input type='text' name='username' value='testuser' disabled></form>";
        Document doc = Jsoup.parse(html);
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertTrue(data.isEmpty());
    }

    @Test
    public void testFormDataWithButton() {
        String html = "<form><input type='button' name='button' value='Click'></form>";
        Document doc = Jsoup.parse(html);
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertTrue(data.isEmpty());
    }

    @Test
    public void testFormDataWithImage() {
        String html = "<form><input type='image' name='image' value='Submit'></form>";
        Document doc = Jsoup.parse(html);
        formElement = ((FormElement) doc.select("form").first());

        List<Connection.KeyVal> data = formElement.formData();
        assertTrue(data.isEmpty());
    }
}
