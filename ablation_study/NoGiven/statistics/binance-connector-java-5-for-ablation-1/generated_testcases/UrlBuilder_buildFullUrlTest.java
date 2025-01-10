
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

public class UrlBuilder_buildFullUrlTest {

    @Test
    public void testBuildFullUrlWithParameters() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/sapi/v1/sub-account/spotSummary";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("page", 1);
        parameters.put("size", 1);

        String expectedUrl = "https://api.binance.com/sapi/v1/sub-account/spotSummary?email=alice%40test.com&page=1&size=1";
        String actualUrl = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters);
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testBuildFullUrlWithoutParameters() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/sapi/v1/sub-account/spotSummary";
        Map<String, Object> parameters = null;

        String expectedUrl = "https://api.binance.com/sapi/v1/sub-account/spotSummary";
        assertEquals(expectedUrl, UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters));
    }

    @Test
    public void testBuildFullUrlWithEmptyParameters() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/sapi/v1/sub-account/spotSummary";
        Map<String, Object> parameters = new HashMap<>();

        String expectedUrl = "https://api.binance.com/sapi/v1/sub-account/spotSummary";
        assertEquals(expectedUrl, UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters));
    }

    @Test
    public void testBuildFullUrlWithDoubleParameter() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/sapi/v1/sub-account/spotSummary";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("amount", 0.123456789);

        String expectedUrl = "https://api.binance.com/sapi/v1/sub-account/spotSummary?email=alice%40test.com&amount=0.123456789";
        String actualUrl = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters);
        assertEquals(expectedUrl, actualUrl);
    }
}
