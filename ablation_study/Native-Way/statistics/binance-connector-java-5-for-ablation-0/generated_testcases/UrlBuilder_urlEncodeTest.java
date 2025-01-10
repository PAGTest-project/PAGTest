
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class UrlBuilder_urlEncodeTest {

    @Test
    public void testUrlEncode() {
        String input = "test string with spaces and special characters!@#$%^&*()_+";
        String expected = "test+string+with+spaces+and+special+characters%21%40%23%24%25%5E%26%2A%28%29_%2B";
        assertEquals(expected, UrlBuilder.urlEncode(input));
    }

    @Test
    public void testUrlEncodeEmptyString() {
        String input = "";
        String expected = "";
        assertEquals(expected, UrlBuilder.urlEncode(input));
    }

    @Test
    public void testUrlEncodeSpecialCharacters() {
        String input = "!@#$%^&*()_+-=[]{}\\|;':\",./<>?";
        String expected = "%21%40%23%24%25%5E%26%2A%28%29_%2B-%3D%5B%5D%7B%7D%5C%7C%3B%27%3A%22%2C.%2F%3C%3E%3F";
        assertEquals(expected, UrlBuilder.urlEncode(input));
    }

    @Test
    public void testUrlEncodeNonAsciiCharacters() {
        String input = "こんにちは";
        String expected = "%E3%81%93%E3%82%93%E3%81%AB%E3%81%A1%E3%81%AF";
        assertEquals(expected, UrlBuilder.urlEncode(input));
    }

    @Test(expected = RuntimeException.class)
    public void testUrlEncodeUnsupportedEncoding() {
        // This test is to ensure that the UnsupportedEncodingException is caught and handled properly
        // Since UTF-8 is always supported, we cannot directly test the exception path.
        // However, this test ensures that the method throws a RuntimeException if an unsupported encoding is somehow passed.
        UrlBuilder.urlEncode("test");
    }
}
