
package com.binance.connector.client.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import okhttp3.Request;

public class RequestBuilder_buildPublicRequestTest {

    private static final String VALID_URL = "https://api.binance.com/api/v3/ticker/price";
    private static final String INVALID_URL = "invalid-url";

    @Test
    public void testBuildPublicRequestPost() {
        Request request = RequestBuilder.buildPublicRequest(VALID_URL, HttpMethod.POST);
        assertEquals(HttpMethod.POST.name(), request.method());
        assertEquals(VALID_URL, request.url().toString());
        assertTrue(request.headers().names().contains("User-Agent"));
        assertTrue(request.headers().names().contains("Content-Type"));
    }

    @Test
    public void testBuildPublicRequestGet() {
        Request request = RequestBuilder.buildPublicRequest(VALID_URL, HttpMethod.GET);
        assertEquals(HttpMethod.GET.name(), request.method());
        assertEquals(VALID_URL, request.url().toString());
        assertTrue(request.headers().names().contains("User-Agent"));
        assertTrue(request.headers().names().contains("Content-Type"));
    }

    @Test
    public void testBuildPublicRequestPut() {
        Request request = RequestBuilder.buildPublicRequest(VALID_URL, HttpMethod.PUT);
        assertEquals(HttpMethod.PUT.name(), request.method());
        assertEquals(VALID_URL, request.url().toString());
        assertTrue(request.headers().names().contains("User-Agent"));
        assertTrue(request.headers().names().contains("Content-Type"));
    }

    @Test
    public void testBuildPublicRequestDelete() {
        Request request = RequestBuilder.buildPublicRequest(VALID_URL, HttpMethod.DELETE);
        assertEquals(HttpMethod.DELETE.name(), request.method());
        assertEquals(VALID_URL, request.url().toString());
        assertTrue(request.headers().names().contains("User-Agent"));
        assertTrue(request.headers().names().contains("Content-Type"));
    }

    @Test
    public void testBuildPublicRequestInvalidMethod() {
        assertThrows(BinanceConnectorException.class, () -> {
            RequestBuilder.buildPublicRequest(VALID_URL, null);
        });
    }

    @Test
    public void testBuildPublicRequestInvalidUrl() {
        assertThrows(BinanceConnectorException.class, () -> {
            RequestBuilder.buildPublicRequest(INVALID_URL, HttpMethod.GET);
        });
    }
}
