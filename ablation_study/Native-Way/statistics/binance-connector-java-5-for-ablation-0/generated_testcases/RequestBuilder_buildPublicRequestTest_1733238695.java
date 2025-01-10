
package com.binance.connector.client.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import okhttp3.Request;

public class RequestBuilder_buildPublicRequestTest {

    private static final String VALID_URL = "https://api.binance.com/api/v3/order";
    private static final String INVALID_URL = "invalid-url";

    @Test
    public void testBuildPublicRequestPost() {
        Request request = RequestBuilder.buildPublicRequest(VALID_URL, HttpMethod.POST);
        assertEquals(HttpMethod.POST.toString(), request.method());
        assertEquals(VALID_URL, request.url().toString());
    }

    @Test
    public void testBuildPublicRequestGet() {
        Request request = RequestBuilder.buildPublicRequest(VALID_URL, HttpMethod.GET);
        assertEquals(HttpMethod.GET.toString(), request.method());
        assertEquals(VALID_URL, request.url().toString());
    }

    @Test
    public void testBuildPublicRequestPut() {
        Request request = RequestBuilder.buildPublicRequest(VALID_URL, HttpMethod.PUT);
        assertEquals(HttpMethod.PUT.toString(), request.method());
        assertEquals(VALID_URL, request.url().toString());
    }

    @Test
    public void testBuildPublicRequestDelete() {
        Request request = RequestBuilder.buildPublicRequest(VALID_URL, HttpMethod.DELETE);
        assertEquals(HttpMethod.DELETE.toString(), request.method());
        assertEquals(VALID_URL, request.url().toString());
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
