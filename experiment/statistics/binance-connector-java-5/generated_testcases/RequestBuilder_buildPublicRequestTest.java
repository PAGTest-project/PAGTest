
package com.binance.connector.client.utils;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import okhttp3.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RequestBuilder_buildPublicRequestTest {

    @Test
    public void testBuildPublicRequest_GET() {
        String fullUrl = "https://api.binance.com/api/v3/ticker/price";
        Request request = RequestBuilder.buildPublicRequest(fullUrl, HttpMethod.GET);
        assertEquals(fullUrl, request.url().toString());
        assertEquals("GET", request.method());
    }

    @Test
    public void testBuildPublicRequest_InvalidMethod() {
        String fullUrl = "https://api.binance.com/api/v3/ticker/price";
        assertThrows(BinanceConnectorException.class, () -> RequestBuilder.buildPublicRequest(fullUrl, HttpMethod.INVALID));
    }

    @Test
    public void testBuildPublicRequest_InvalidUrl() {
        String fullUrl = "invalid-url";
        assertThrows(BinanceConnectorException.class, () -> RequestBuilder.buildPublicRequest(fullUrl, HttpMethod.GET));
    }
}
