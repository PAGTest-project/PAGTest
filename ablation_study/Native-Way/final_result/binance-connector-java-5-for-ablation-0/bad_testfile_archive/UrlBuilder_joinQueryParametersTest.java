
package com.binance.connector.client.utils;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

public class UrlBuilder_joinQueryParametersTest {

    private StringBuilder sb;

    @Before
    public void setUp() {
        sb = new StringBuilder();
    }

    @Test
    public void testJoinQueryParametersWithNullParams() {
        Map<String, Object> params = null;
        StringBuilder result = UrlBuilder.joinQueryParameters(sb, params);
        assertEquals(sb.toString(), result.toString());
    }

    @Test
    public void testJoinQueryParametersWithEmptyParams() {
        Map<String, Object> params = new HashMap<>();
        StringBuilder result = UrlBuilder.joinQueryParameters(sb, params);
        assertEquals(sb.toString(), result.toString());
    }

    @Test
    public void testJoinQueryParametersWithSingleParam() {
        Map<String, Object> params = new HashMap<>();
        params.put("key1", "value1");
        StringBuilder expected = new StringBuilder("key1=value1");
        StringBuilder result = UrlBuilder.joinQueryParameters(sb, params);
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void testJoinQueryParametersWithMultipleParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("key1", "value1");
        params.put("key2", 123);
        params.put("key3", 123.456);
        StringBuilder expected = new StringBuilder("key1=value1&key2=123&key3=123.456");
        StringBuilder result = UrlBuilder.joinQueryParameters(sb, params);
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void testJoinQueryParametersWithSpecialCharacters() {
        Map<String, Object> params = new HashMap<>();
        params.put("key1", "value with spaces");
        params.put("key2", "value&with&special&chars");
        StringBuilder expected = new StringBuilder("key1=value%20with%20spaces&key2=value%26with%26special%26chars");
        StringBuilder result = UrlBuilder.joinQueryParameters(sb, params);
        assertEquals(expected.toString(), result.toString());
    }
}
