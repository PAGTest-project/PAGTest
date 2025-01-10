
package com.binance.connector.client.utils;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class UrlBuilder_buildFullUrlTest {
    private String baseUrl;
    private String urlPath;
    private Map<String, Object> parameters;

    @Before
    public void setUp() {
        baseUrl = "https://api.binance.com";
        urlPath = "/sapi/v1/sub-account/status";
        parameters = new HashMap<>();
    }

    @Test
    public void testBuildFullUrlWithoutParameters() {
        String expected = baseUrl + urlPath;
        String result = UrlBuilder.buildFullUrl(baseUrl, urlPath, null);
        assertEquals(expected, result);
    }

    @Test
    public void testBuildFullUrlWithParameters() {
        parameters.put("email", "alice@test.com");
        String expected = baseUrl + urlPath + "?email=alice%40test.com";
        String result = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters);
        assertEquals(expected, result);
    }
}
