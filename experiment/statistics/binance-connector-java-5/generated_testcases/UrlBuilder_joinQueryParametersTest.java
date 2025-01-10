
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
    public void testJoinQueryParametersWithEmptyMap() {
        Map<String, Object> params = new HashMap<>();
        StringBuilder result = UrlBuilder.joinQueryParameters(sb, params);
        assertEquals(sb.toString(), result.toString());
    }

    @Test
    public void testJoinQueryParametersWithNonEmptyMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("key1", "value1");
        params.put("key2", 123.45);

        StringBuilder result = UrlBuilder.joinQueryParameters(sb, params);
        String expected = "key1=value1&key2=123.45";
        assertEquals(expected, result.toString());
    }

    @Test
    public void testJoinQueryParametersWithNullMap() {
        StringBuilder result = UrlBuilder.joinQueryParameters(sb, null);
        assertEquals(sb.toString(), result.toString());
    }
}
