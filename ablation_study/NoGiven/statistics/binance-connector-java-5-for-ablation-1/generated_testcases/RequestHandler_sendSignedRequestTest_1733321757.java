
package com.binance.connector.client.utils;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.signaturegenerator.SignatureGenerator;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

public class RequestHandler_sendSignedRequestTest {

    private RequestHandler requestHandler;
    private SignatureGenerator mockSignatureGenerator;
    private ProxyAuth mockProxyAuth;

    @Before
    public void setUp() {
        mockSignatureGenerator = mock(HmacSignatureGenerator.class);
        mockProxyAuth = mock(ProxyAuth.class);
        requestHandler = new RequestHandler("validApiKey", mockSignatureGenerator, mockProxyAuth);
    }

    @Test
    public void testSendSignedRequest_HmacSignatureGenerator_ValidApiKey() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/api/v3/order";
        Map<String, Object> parameters = new HashMap<>();
        HttpMethod httpMethod = HttpMethod.POST;
        boolean showLimitUsage = true;

        when(mockSignatureGenerator.getSignature(anyString())).thenReturn("mockSignature");
        when(UrlBuilder.buildTimestamp()).thenReturn("1234567890");
        when(UrlBuilder.buildFullUrl(anyString(), anyString(), anyMap())).thenReturn("fullUrl");
        when(ResponseHandler.handleResponse(any(), eq(showLimitUsage), eq(mockProxyAuth))).thenReturn("response");

        String result = requestHandler.sendSignedRequest(baseUrl, urlPath, parameters, httpMethod, showLimitUsage);

        assertNotNull(result);
        verify(mockSignatureGenerator).getSignature(anyString());
        verify(UrlBuilder).buildTimestamp();
        verify(UrlBuilder).buildFullUrl(baseUrl, urlPath, parameters);
        verify(ResponseHandler).handleResponse(any(), eq(showLimitUsage), eq(mockProxyAuth));
    }

    @Test(expected = BinanceConnectorException.class)
    public void testSendSignedRequest_HmacSignatureGenerator_NullApiKey() {
        requestHandler = new RequestHandler(null, mockSignatureGenerator, mockProxyAuth);
        requestHandler.sendSignedRequest("baseUrl", "urlPath", null, HttpMethod.POST, true);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testSendSignedRequest_HmacSignatureGenerator_EmptyApiKey() {
        requestHandler = new RequestHandler("", mockSignatureGenerator, mockProxyAuth);
        requestHandler.sendSignedRequest("baseUrl", "urlPath", null, HttpMethod.POST, true);
    }
}
