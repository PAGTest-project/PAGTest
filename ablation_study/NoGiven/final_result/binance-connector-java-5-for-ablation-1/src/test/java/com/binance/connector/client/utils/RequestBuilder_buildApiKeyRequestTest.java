
package com.binance.connector.client.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import okhttp3.Request;

public class RequestBuilder_buildApiKeyRequestTest {

    private static final String VALID_URL = "https://api.binance.com";
    private static final String INVALID_URL = "invalid-url";
    private static final String API_KEY = "test-api-key";

    @Test
    public void testBuildApiKeyRequestPost() {
        Request request = RequestBuilder.buildApiKeyRequest(VALID_URL, HttpMethod.POST, API_KEY);
        assertEquals(HttpMethod.POST.name(), request.method());
        assertEquals(API_KEY, request.header("X-MBX-APIKEY"));
    }

    @Test
    public void testBuildApiKeyRequestGet() {
        Request request = RequestBuilder.buildApiKeyRequest(VALID_URL, HttpMethod.GET, API_KEY);
        assertEquals(HttpMethod.GET.name(), request.method());
        assertEquals(API_KEY, request.header("X-MBX-APIKEY"));
    }

    @Test
    public void testBuildApiKeyRequestPut() {
        Request request = RequestBuilder.buildApiKeyRequest(VALID_URL, HttpMethod.PUT, API_KEY);
        assertEquals(HttpMethod.PUT.name(), request.method());
        assertEquals(API_KEY, request.header("X-MBX-APIKEY"));
    }

    @Test
    public void testBuildApiKeyRequestDelete() {
        Request request = RequestBuilder.buildApiKeyRequest(VALID_URL, HttpMethod.DELETE, API_KEY);
        assertEquals(HttpMethod.DELETE.name(), request.method());
        assertEquals(API_KEY, request.header("X-MBX-APIKEY"));
    }

    @Test
    public void testBuildApiKeyRequestInvalidMethod() {
        BinanceConnectorException thrown = assertThrows(BinanceConnectorException.class, () -> RequestBuilder.buildApiKeyRequest(VALID_URL, HttpMethod.INVALID, API_KEY));
        assertTrue(thrown.getMessage().contains("Invalid HTTP method: "));
    }

    @Test
    public void testBuildApiKeyRequestInvalidUrl() {
        BinanceConnectorException thrown = assertThrows(BinanceConnectorException.class, () -> RequestBuilder.buildApiKeyRequest(INVALID_URL, HttpMethod.GET, API_KEY));
        assertTrue(thrown.getMessage().contains("Invalid URL: "));
    }
}
