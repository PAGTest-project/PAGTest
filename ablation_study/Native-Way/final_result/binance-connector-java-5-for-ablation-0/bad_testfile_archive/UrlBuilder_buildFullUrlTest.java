
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

public class UrlBuilder_buildFullUrlTest {

    @Test
    public void testBuildFullUrlWithParameters() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/api/v3/order";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "LIMIT");
        parameters.put("timeInForce", "GTC");
        parameters.put("quantity", 1.0);
        parameters.put("price", 50000.0);

        String expectedUrl = "https://api.binance.com/api/v3/order?symbol=BTCUSDT&side=BUY&type=LIMIT&timeInForce=GTC&quantity=1&price=50000";
        String actualUrl = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters);
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testBuildFullUrlWithoutParameters() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/api/v3/order";
        Map<String, Object> parameters = null;

        String expectedUrl = "https://api.binance.com/api/v3/order";
        assertEquals(expectedUrl, UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters));
    }

    @Test
    public void testBuildFullUrlWithEmptyParameters() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/api/v3/order";
        Map<String, Object> parameters = new HashMap<>();

        String expectedUrl = "https://api.binance.com/api/v3/order";
        assertEquals(expectedUrl, UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters));
    }
}
