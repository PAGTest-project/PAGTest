
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class UrlBuilder_joinQueryParametersTest {

    @Test
    public void testJoinQueryParametersWithEmptyParams() {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new LinkedHashMap<>();
        assertEquals(sb.toString(), UrlBuilder.joinQueryParameters(sb, params).toString());
    }

    @Test
    public void testJoinQueryParametersWithSingleParam() {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("key1", "value1");
        String expected = "key1=value1";
        assertEquals(expected, UrlBuilder.joinQueryParameters(sb, params).toString());
    }

    @Test
    public void testJoinQueryParametersWithMultipleParams() {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("key1", "value1");
        params.put("key2", 123);
        params.put("key3", 123.456);
        String expected = "key1=value1&key2=123&key3=123.456";
        assertEquals(expected, UrlBuilder.joinQueryParameters(sb, params).toString());
    }

    @Test
    public void testJoinQueryParametersWithNullParams() {
        StringBuilder sb = new StringBuilder();
        assertEquals(sb.toString(), UrlBuilder.joinQueryParameters(sb, null).toString());
    }

    @Test
    public void testJoinQueryParametersWithSpecialCharacters() {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("key1", "value with spaces");
        params.put("key2", "value&with&special&chars");
        String expected = "key1=value%20with%20spaces&key2=value%26with%26special%26chars";
        assertEquals(expected, UrlBuilder.joinQueryParameters(sb, params).toString());
    }

    @Test
    public void testJoinQueryParametersWithDoubleValue() {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("key1", 123.456);
        String expected = "key1=123.456";
        assertEquals(expected, UrlBuilder.joinQueryParameters(sb, params).toString());
    }
}
