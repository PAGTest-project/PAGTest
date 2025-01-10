
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
import org.junit.Before;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class ResponseHandler_handleResponseTest {

    private ProxyAuth mockProxy;
    private OkHttpClient mockClient;
    private Response mockResponse;
    private ResponseBody mockResponseBody;

    @Before
    public void setUp() {
        mockProxy = new ProxyAuth(); // Create a real instance instead of mocking
        mockClient = mock(OkHttpClient.class);
        mockResponse = mock(Response.class);
        mockResponseBody = mock(ResponseBody.class);

        when(HttpClientSingleton.getHttpClient(any(ProxyAuth.class))).thenReturn(mockClient);
    }

    @Test
    public void testHandleResponse_SuccessfulResponse_ShowLimitUsage() throws IOException {
        Request mockRequest = new Request.Builder().url("http://example.com").build();
        when(mockClient.newCall(mockRequest).execute()).thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockResponse.code()).thenReturn(200);
        when(mockResponseBody.string()).thenReturn("{}");
        when(mockResponse.header("X-SAPI-USED-IP-WEIGHT-1M")).thenReturn("100");
        when(mockResponse.header("X-SAPI-USED-UID-WEIGHT-1M")).thenReturn("200");
        when(mockResponse.header("x-mbx-used-weight")).thenReturn("300");
        when(mockResponse.header("x-mbx-used-weight-1m")).thenReturn("400");

        String result = ResponseHandler.handleResponse(mockRequest, true, mockProxy);

        assertEquals("{\"x-sapi-used-ip-weight-1m\":\"100\",\"x-sapi-used-uid-weight-1m\":\"200\",\"x-mbx-used-weight\":\"300\",\"x-mbx-used-weight-1m\":\"400\",\"data\":\"{}\"}", result);
    }

    @Test
    public void testHandleResponse_SuccessfulResponse_NoShowLimitUsage() throws IOException {
        Request mockRequest = new Request.Builder().url("http://example.com").build();
        when(mockClient.newCall(mockRequest).execute()).thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockResponse.code()).thenReturn(200);
        when(mockResponseBody.string()).thenReturn("{}");

        String result = ResponseHandler.handleResponse(mockRequest, false, mockProxy);

        assertEquals("{}", result);
    }

    @Test(expected = BinanceServerException.class)
    public void testHandleResponse_NoResponseFromServer() throws IOException {
        Request mockRequest = new Request.Builder().url("http://example.com").build();
        when(mockClient.newCall(mockRequest).execute()).thenReturn(null);

        ResponseHandler.handleResponse(mockRequest, false, mockProxy);
    }

    @Test(expected = BinanceClientException.class)
    public void testHandleResponse_ClientError() throws IOException {
        Request mockRequest = new Request.Builder().url("http://example.com").build();
        when(mockClient.newCall(mockRequest).execute()).thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockResponse.code()).thenReturn(400);
        when(mockResponseBody.string()).thenReturn("{\"code\":-1000,\"msg\":\"Invalid request\"}");

        ResponseHandler.handleResponse(mockRequest, false, mockProxy);
    }

    @Test(expected = BinanceServerException.class)
    public void testHandleResponse_ServerError() throws IOException {
        Request mockRequest = new Request.Builder().url("http://example.com").build();
        when(mockClient.newCall(mockRequest).execute()).thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockResponse.code()).thenReturn(500);
        when(mockResponseBody.string()).thenReturn("Internal Server Error");

        ResponseHandler.handleResponse(mockRequest, false, mockProxy);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testHandleResponse_IOException() throws IOException {
        Request mockRequest = new Request.Builder().url("http://example.com").build();
        when(mockClient.newCall(mockRequest).execute()).thenThrow(new IOException("Connection reset"));

        ResponseHandler.handleResponse(mockRequest, false, mockProxy);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testHandleResponse_ConnectException() throws IOException {
        Request mockRequest = new Request.Builder().url("http://example.com").build();
        when(mockClient.newCall(mockRequest).execute()).thenThrow(new ConnectException("Connection refused"));

        ResponseHandler.handleResponse(mockRequest, false, mockProxy);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testHandleResponse_UnknownHostException() throws IOException {
        Request mockRequest = new Request.Builder().url("http://example.com").build();
        when(mockClient.newCall(mockRequest).execute()).thenThrow(new UnknownHostException("Unknown host"));

        ResponseHandler.handleResponse(mockRequest, false, mockProxy);
    }
}
