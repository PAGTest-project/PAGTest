
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
        formElement = new FormElement(Tag.valueOf("form"), null, null);
    }

    @Test
    public void testFormDataWithSingleInput() {
        Element input = new Element(Tag.valueOf("input"), null);
        input.attr("name", "username");
        input.attr("value", "testuser");
        formElement.appendChild(input);

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("username", data.get(0).key());
        assertEquals("testuser", data.get(0).value());
    }

    @Test
    public void testFormDataWithDisabledInput() {
        Element input = new Element(Tag.valueOf("input"), null);
        input.attr("name", "username");
        input.attr("value", "testuser");
        input.attr("disabled", "disabled");
        formElement.appendChild(input);

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(0, data.size());
    }

    @Test
    public void testFormDataWithSelect() {
        Element select = new Element(Tag.valueOf("select"), null);
        select.attr("name", "color");
        Element option1 = new Element(Tag.valueOf("option"), null);
        option1.attr("value", "red");
        option1.attr("selected", "selected");
        Element option2 = new Element(Tag.valueOf("option"), null);
        option2.attr("value", "blue");
        select.appendChild(option1);
        select.appendChild(option2);
        formElement.appendChild(select);

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("color", data.get(0).key());
        assertEquals("red", data.get(0).value());
    }

    @Test
    public void testFormDataWithCheckbox() {
        Element checkbox = new Element(Tag.valueOf("input"), null);
        checkbox.attr("type", "checkbox");
        checkbox.attr("name", "remember");
        checkbox.attr("checked", "checked");
        formElement.appendChild(checkbox);

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("remember", data.get(0).key());
        assertEquals("on", data.get(0).value());
    }

    @Test
    public void testFormDataWithRadio() {
        Element radio1 = new Element(Tag.valueOf("input"), null);
        radio1.attr("type", "radio");
        radio1.attr("name", "gender");
        radio1.attr("value", "male");
        radio1.attr("checked", "checked");
        Element radio2 = new Element(Tag.valueOf("input"), null);
        radio2.attr("type", "radio");
        radio2.attr("name", "gender");
        radio2.attr("value", "female");
        formElement.appendChild(radio1);
        formElement.appendChild(radio2);

        List<Connection.KeyVal> data = formElement.formData();
        assertEquals(1, data.size());
        assertEquals("gender", data.get(0).key());
        assertEquals("male", data.get(0).value());
    }
}
