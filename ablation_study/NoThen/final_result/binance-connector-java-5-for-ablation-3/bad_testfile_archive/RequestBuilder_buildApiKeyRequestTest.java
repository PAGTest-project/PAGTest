
package com.binance.connector.client.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import okhttp3.Request;

public class RequestBuilder_buildApiKeyRequestTest {

    private static final String VALID_URL = "https://api.binance.com/api/v3/order";
    private static final String INVALID_URL = "invalid-url";
    private static final String API_KEY = "test-api-key";

    @Test
    public void testBuildApiKeyRequestPost() {
        Request request = RequestBuilder.buildApiKeyRequest(VALID_URL, HttpMethod.POST, API_KEY);
        assertEquals(HttpMethod.POST.toString(), request.method());
        assertEquals(VALID_URL, request.url().toString());
        assertEquals(API_KEY, request.header("X-MBX-APIKEY"));
    }

    @Test
    public void testBuildApiKeyRequestGet() {
        Request request = RequestBuilder.buildApiKeyRequest(VALID_URL, HttpMethod.GET, API_KEY);
        assertEquals(HttpMethod.GET.toString(), request.method());
        assertEquals(VALID_URL, request.url().toString());
        assertEquals(API_KEY, request.header("X-MBX-APIKEY"));
    }

    @Test
    public void testBuildApiKeyRequestPut() {
        Request request = RequestBuilder.buildApiKeyRequest(VALID_URL, HttpMethod.PUT, API_KEY);
        assertEquals(HttpMethod.PUT.toString(), request.method());
        assertEquals(VALID_URL, request.url().toString());
        assertEquals(API_KEY, request.header("X-MBX-APIKEY"));
    }

    @Test
    public void testBuildApiKeyRequestDelete() {
        Request request = RequestBuilder.buildApiKeyRequest(VALID_URL, HttpMethod.DELETE, API_KEY);
        assertEquals(HttpMethod.DELETE.toString(), request.method());
        assertEquals(VALID_URL, request.url().toString());
        assertEquals(API_KEY, request.header("X-MBX-APIKEY"));
    }

    @Test
    public void testBuildApiKeyRequestInvalidHttpMethod() {
        assertThrows(BinanceConnectorException.class, () -> {
            RequestBuilder.buildApiKeyRequest(VALID_URL, null, API_KEY);
        });
    }

    @Test
    public void testBuildApiKeyRequestInvalidUrl() {
        assertThrows(BinanceConnectorException.class, () -> {
            RequestBuilder.buildApiKeyRequest(INVALID_URL, HttpMethod.GET, API_KEY);
        });
    }
}
