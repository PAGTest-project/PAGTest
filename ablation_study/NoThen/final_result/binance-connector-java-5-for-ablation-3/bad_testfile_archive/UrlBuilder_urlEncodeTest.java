
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class UrlBuilder_urlEncodeTest {

    @Test
    public void testUrlEncodeSimpleString() {
        String input = "hello world";
        String expected = "hello+world";
        assertEquals(expected, UrlBuilder.urlEncode(input));
    }

    @Test
    public void testUrlEncodeSpecialCharacters() {
        String input = "!@#$%^&*()_+-={}[]|\\:;\"'<>,.?/";
        String expected = "%21%40%23%24%25%5E%26%2A%28%29_%2B-%3D%7B%7D%5B%5D%7C%5C%3A%3B%22%27%3C%3E%2C.%3F%2F";
        assertEquals(expected, UrlBuilder.urlEncode(input));
    }

    @Test
    public void testUrlEncodeEmptyString() {
        String input = "";
        String expected = "";
        assertEquals(expected, UrlBuilder.urlEncode(input));
    }

    @Test
    public void testUrlEncodeNullString() {
        String input = null;
        String expected = "";
        assertEquals(expected, UrlBuilder.urlEncode(input == null ? "" : input));
    }

    @Test
    public void testUrlEncodeUnicodeCharacters() {
        String input = "こんにちは";
        String expected = "%E3%81%93%E3%82%93%E3%81%AB%E3%81%A1%E3%81%AF";
        assertEquals(expected, UrlBuilder.urlEncode(input));
    }
}
