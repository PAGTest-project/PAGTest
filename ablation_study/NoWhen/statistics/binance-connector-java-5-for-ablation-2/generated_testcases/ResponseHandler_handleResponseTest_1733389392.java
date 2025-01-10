
package com.binance.connector.client.utils;

import com.binance.connector.client.exceptions.BinanceClientException;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.exceptions.BinanceServerException;
import com.binance.connector.client.utils.httpclient.HttpClientSingleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResponseHandler_handleResponseTest {

    @Mock
    private OkHttpClient mockClient;

    @Mock
    private ResponseBody mockResponseBody;

    @Test
    public void testHandleResponse_SuccessfulResponse_ShowLimitUsage() throws IOException {
        Request mockRequest = new Request.Builder().url("http://test.com").build();
        ProxyAuth mockProxy = mock(ProxyAuth.class);

        Response mockResponse = mock(Response.class);
        when(HttpClientSingleton.getHttpClient(mockProxy)).thenReturn(mockClient);
        when(mockClient.newCall(mockRequest).execute()).thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockResponseBody.string()).thenReturn("{\"key\":\"value\"}");
        when(mockResponse.code()).thenReturn(200);
        when(mockResponse.header("X-SAPI-USED-IP-WEIGHT-1M")).thenReturn("100");
        when(mockResponse.header("X-SAPI-USED-UID-WEIGHT-1M")).thenReturn("200");
        when(mockResponse.header("x-mbx-used-weight")).thenReturn("300");
        when(mockResponse.header("x-mbx-used-weight-1m")).thenReturn("400");

        String result = ResponseHandler.handleResponse(mockRequest, true, mockProxy);

        assertEquals("{\"x-sapi-used-ip-weight-1m\":\"100\",\"x-sapi-used-uid-weight-1m\":\"200\",\"x-mbx-used-weight\":\"300\",\"x-mbx-used-weight-1m\":\"400\",\"data\":\"{\\\"key\\\":\\\"value\\\"}\"}", result);
    }

    @Test
    public void testHandleResponse_ClientError() throws IOException {
        Request mockRequest = new Request.Builder().url("http://test.com").build();
        ProxyAuth mockProxy = mock(ProxyAuth.class);

        Response mockResponse = mock(Response.class);
        when(HttpClientSingleton.getHttpClient(mockProxy)).thenReturn(mockClient);
        when(mockClient.newCall(mockRequest).execute()).thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockResponseBody.string()).thenReturn("{\"code\":-1000,\"msg\":\"Test Error\"}");
        when(mockResponse.code()).thenReturn(400);

        try {
            ResponseHandler.handleResponse(mockRequest, false, mockProxy);
            fail("Expected BinanceClientException to be thrown");
        } catch (BinanceClientException e) {
            assertEquals("{\"code\":-1000,\"msg\":\"Test Error\"}", e.getMessage());
        }
    }

    @Test
    public void testHandleResponse_ServerError() throws IOException {
        Request mockRequest = new Request.Builder().url("http://test.com").build();
        ProxyAuth mockProxy = mock(ProxyAuth.class);

        Response mockResponse = mock(Response.class);
        when(HttpClientSingleton.getHttpClient(mockProxy)).thenReturn(mockClient);
        when(mockClient.newCall(mockRequest).execute()).thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockResponseBody.string()).thenReturn("Server Error");
        when(mockResponse.code()).thenReturn(500);

        try {
            ResponseHandler.handleResponse(mockRequest, false, mockProxy);
            fail("Expected BinanceServerException to be thrown");
        } catch (BinanceServerException e) {
            assertEquals("Server Error", e.getMessage());
            assertEquals(500, e.getHttpStatusCode());
        }
    }

    @Test
    public void testHandleResponse_IOException() throws IOException {
        Request mockRequest = new Request.Builder().url("http://test.com").build();
        ProxyAuth mockProxy = mock(ProxyAuth.class);

        when(HttpClientSingleton.getHttpClient(mockProxy)).thenReturn(mockClient);
        when(mockClient.newCall(mockRequest).execute()).thenThrow(new IOException("Test IO Exception"));

        try {
            ResponseHandler.handleResponse(mockRequest, false, mockProxy);
            fail("Expected BinanceConnectorException to be thrown");
        } catch (BinanceConnectorException e) {
            assertEquals("[ResponseHandler] OKHTTP Error: Test IO Exception", e.getMessage());
        }
    }

    @Test
    public void testHandleResponse_ConnectException() throws IOException {
        Request mockRequest = new Request.Builder().url("http://test.com").build();
        ProxyAuth mockProxy = mock(ProxyAuth.class);

        when(HttpClientSingleton.getHttpClient(mockProxy)).thenReturn(mockClient);
        when(mockClient.newCall(mockRequest).execute()).thenThrow(new ConnectException("Test Connect Exception"));

        try {
            ResponseHandler.handleResponse(mockRequest, false, mockProxy);
            fail("Expected BinanceConnectorException to be thrown");
        } catch (BinanceConnectorException e) {
            assertEquals("[ResponseHandler] Proxy Connection Error: Test Connect Exception", e.getMessage());
        }
    }

    @Test
    public void testHandleResponse_UnknownHostException() throws IOException {
        Request mockRequest = new Request.Builder().url("http://test.com").build();
        ProxyAuth mockProxy = mock(ProxyAuth.class);

        when(HttpClientSingleton.getHttpClient(mockProxy)).thenReturn(mockClient);
        when(mockClient.newCall(mockRequest).execute()).thenThrow(new UnknownHostException("Test Unknown Host Exception"));

        try {
            ResponseHandler.handleResponse(mockRequest, false, mockProxy);
            fail("Expected BinanceConnectorException to be thrown");
        } catch (BinanceConnectorException e) {
            assertEquals("[ResponseHandler] Proxy Unknown Host Error: Test Unknown Host Exception", e.getMessage());
        }
    }
}
