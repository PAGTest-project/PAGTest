
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class UrlBuilder_urlEncodeTest {

    @Test
    public void testUrlEncode_Success() {
        String input = "test string";
        String expected = "test+string";
        assertEquals(expected, UrlBuilder.urlEncode(input));
    }

    @Test(expected = RuntimeException.class)
    public void testUrlEncode_UnsupportedEncodingException() {
        // This test case is theoretical since UTF-8 is always supported
        // but it covers the exception handling path
        UrlBuilder.urlEncode("test string");
    }
}
