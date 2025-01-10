
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
        formElement.addElement(input);

        List<Connection.KeyVal> formData = formElement.formData();
        assertEquals(1, formData.size());
        assertEquals("username", formData.get(0).key());
        assertEquals("testuser", formData.get(0).value());
    }

    @Test
    public void testFormDataWithDisabledInput() {
        Element input = new Element(Tag.valueOf("input"), null);
        input.attr("name", "username");
        input.attr("value", "testuser");
        input.attr("disabled", "disabled");
        formElement.addElement(input);

        List<Connection.KeyVal> formData = formElement.formData();
        assertTrue(formData.isEmpty());
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
        formElement.addElement(select);

        List<Connection.KeyVal> formData = formElement.formData();
        assertEquals(1, formData.size());
        assertEquals("color", formData.get(0).key());
        assertEquals("red", formData.get(0).value());
    }

    @Test
    public void testFormDataWithCheckbox() {
        Element checkbox = new Element(Tag.valueOf("input"), null);
        checkbox.attr("type", "checkbox");
        checkbox.attr("name", "subscribe");
        checkbox.attr("checked", "checked");
        formElement.addElement(checkbox);

        List<Connection.KeyVal> formData = formElement.formData();
        assertEquals(1, formData.size());
        assertEquals("subscribe", formData.get(0).key());
        assertEquals("on", formData.get(0).value());
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
        formElement.addElement(radio1);
        formElement.addElement(radio2);

        List<Connection.KeyVal> formData = formElement.formData();
        assertEquals(1, formData.size());
        assertEquals("gender", formData.get(0).key());
        assertEquals("male", formData.get(0).value());
    }

    @Test
    public void testFormDataWithButton() {
        Element button = new Element(Tag.valueOf("input"), null);
        button.attr("type", "button");
        button.attr("name", "submit");
        button.attr("value", "Submit");
        formElement.addElement(button);

        List<Connection.KeyVal> formData = formElement.formData();
        assertTrue(formData.isEmpty());
    }

    @Test
    public void testFormDataWithImage() {
        Element image = new Element(Tag.valueOf("input"), null);
        image.attr("type", "image");
        image.attr("name", "imageButton");
        image.attr("value", "Image");
        formElement.addElement(image);

        List<Connection.KeyVal> formData = formElement.formData();
        assertTrue(formData.isEmpty());
    }
}
