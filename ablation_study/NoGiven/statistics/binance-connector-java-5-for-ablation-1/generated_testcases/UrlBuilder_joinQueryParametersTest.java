
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

public class UrlBuilder_joinQueryParametersTest {

    @Test
    public void testJoinQueryParametersWithNullParams() {
        StringBuilder sb = new StringBuilder();
        StringBuilder result = UrlBuilder.joinQueryParameters(sb, null);
        assertEquals(sb.toString(), result.toString());
    }

    @Test
    public void testJoinQueryParametersWithEmptyParams() {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        StringBuilder result = UrlBuilder.joinQueryParameters(sb, params);
        assertEquals(sb.toString(), result.toString());
    }

    @Test
    public void testJoinQueryParametersWithStringParams() {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        params.put("key1", "value1");
        params.put("key2", "value2");
        StringBuilder result = UrlBuilder.joinQueryParameters(sb, params);
        assertEquals("key1=value1&key2=value2", result.toString());
    }

    @Test
    public void testJoinQueryParametersWithDoubleParams() {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        params.put("key1", 123.456);
        params.put("key2", 789.012);
        StringBuilder result = UrlBuilder.joinQueryParameters(sb, params);
        assertEquals("key1=123.456&key2=789.012", result.toString());
    }

    @Test
    public void testJoinQueryParametersWithMixedParams() {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        params.put("key1", "value1");
        params.put("key2", 123.456);
        StringBuilder result = UrlBuilder.joinQueryParameters(sb, params);
        assertEquals("key1=value1&key2=123.456", result.toString());
    }
}
