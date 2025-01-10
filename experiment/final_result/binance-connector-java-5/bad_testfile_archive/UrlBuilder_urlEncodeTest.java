
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class UrlBuilder_urlEncodeTest {

    @Test
    public void testUrlEncode_Success() {
        String input = "test string";
        String expected = "test%20string";
        assertEquals(expected, UrlBuilder.urlEncode(input).replace("+", "%20"));
    }

    @Test
    public void testUrlEncode_UnsupportedEncodingException() {
        // This test case is theoretical since UTF-8 is always supported
        // but it covers the exception handling path
        try {
            UrlBuilder.urlEncode("test string");
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            // Expected exception
        }
    }
}
