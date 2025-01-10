
package com.binance.connector.client.utils;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import okhttp3.Request;
import org.junit.Test;
import static org.junit.Assert.*;

public class RequestBuilder_buildApiKeyRequestTest {

    @Test
    public void testBuildApiKeyRequest_GET() {
        String fullUrl = "https://api.binance.com/api/v3/ticker/price";
        String apiKey = "testApiKey";
        Request request = RequestBuilder.buildApiKeyRequest(fullUrl, HttpMethod.GET, apiKey);
        assertNotNull(request);
        assertEquals(fullUrl, request.url().toString());
        assertEquals(apiKey, request.header("X-MBX-APIKEY"));
    }

    @Test(expected = BinanceConnectorException.class)
    public void testBuildApiKeyRequest_InvalidHttpMethod() {
        String fullUrl = "https://api.binance.com/api/v3/ticker/price";
        String apiKey = "testApiKey";
        RequestBuilder.buildApiKeyRequest(fullUrl, HttpMethod.PATCH, apiKey);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testBuildApiKeyRequest_InvalidURL() {
        String fullUrl = "invalidUrl";
        String apiKey = "testApiKey";
        RequestBuilder.buildApiKeyRequest(fullUrl, HttpMethod.GET, apiKey);
    }
}
