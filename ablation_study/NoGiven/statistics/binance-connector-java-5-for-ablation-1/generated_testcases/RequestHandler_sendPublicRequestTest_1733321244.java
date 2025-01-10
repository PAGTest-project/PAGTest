
package com.binance.connector.client.utils;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.RequestBuilder;
import com.binance.connector.client.utils.ResponseHandler;
import com.binance.connector.client.utils.UrlBuilder;
import com.binance.connector.client.utils.ProxyAuth;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RequestHandler_sendPublicRequestTest {

    private RequestHandler requestHandler;
    private ProxyAuth proxy;

    @Before
    public void setUp() {
        proxy = mock(ProxyAuth.class);
        requestHandler = new RequestHandler("apiKey", proxy);
    }

    @Test
    public void testSendPublicRequest() {
        // Given
        String baseUrl = "https://api.binance.com";
        String urlPath = "/api/v3/ticker/price";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "BTCUSDT");
        HttpMethod httpMethod = HttpMethod.GET;
        boolean showLimitUsage = true;

        String expectedFullUrl = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
        Request expectedRequest = mock(Request.class);
        String expectedResponse = "response";

        // When
        when(UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters)).thenReturn(expectedFullUrl);
        when(RequestBuilder.buildPublicRequest(expectedFullUrl, httpMethod)).thenReturn(expectedRequest);
        when(ResponseHandler.handleResponse(expectedRequest, showLimitUsage, proxy)).thenReturn(expectedResponse);

        String actualResponse = requestHandler.sendPublicRequest(baseUrl, urlPath, parameters, httpMethod, showLimitUsage);

        // Then
        assertEquals(expectedResponse, actualResponse);
        verify(UrlBuilder).buildFullUrl(baseUrl, urlPath, parameters);
        verify(RequestBuilder).buildPublicRequest(expectedFullUrl, httpMethod);
        verify(ResponseHandler).handleResponse(expectedRequest, showLimitUsage, proxy);
    }
}
