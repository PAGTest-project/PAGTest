
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

public class UrlBuilder_buildFullUrlTest {

    @Test
    public void testBuildFullUrlWithoutParameters() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/v3/order";
        Map<String, Object> parameters = null;

        String expected = "https://api.binance.com/v3/order";
        String actual = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters);

        assertEquals(expected, actual);
    }

    @Test
    public void testBuildFullUrlWithParameters() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/v3/order";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "LIMIT");
        parameters.put("timeInForce", "GTC");
        parameters.put("quantity", 0.01);
        parameters.put("price", 50000.0);

        String expected = "https://api.binance.com/v3/order?symbol=BTCUSDT&side=BUY&type=LIMIT&timeInForce=GTC&quantity=0.01&price=50000.0";
        String actual = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters);

        assertEquals(expected, actual);
    }

    @Test
    public void testBuildFullUrlWithEmptyParameters() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/v3/order";
        Map<String, Object> parameters = new HashMap<>();

        String expected = "https://api.binance.com/v3/order";
        String actual = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters);

        assertEquals(expected, actual);
    }

    @Test
    public void testBuildFullUrlWithEncodedParameters() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/v3/order";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("asset", "BNB");
        parameters.put("amount", 0.1);

        String expected = "https://api.binance.com/v3/order?email=alice%40test.com&asset=BNB&amount=0.1";
        String actual = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters);

        assertEquals(expected, actual);
    }
}
