
package com.binance.connector.client.utils;

import com.binance.connector.client.exceptions.BinanceClientException;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.exceptions.BinanceServerException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResponseHandler_handleResponseTest {

    private Request mockRequest;
    private ProxyAuth mockProxy;
    private OkHttpClient mockClient;
    private Response mockResponse;
    private ResponseBody mockResponseBody;

    @Before
    public void setUp() {
        mockRequest = mock(Request.class);
        mockProxy = mock(ProxyAuth.class);
        mockClient = mock(OkHttpClient.class);
        mockResponse = mock(Response.class);
        mockResponseBody = mock(ResponseBody.class);
    }

    @Test
    public void testHandleResponseSuccess() throws IOException {
        when(mockClient.newCall(mockRequest)).thenReturn(new okhttp3.Call() {
            @Override
            public Response execute() throws IOException {
                return mockResponse;
            }

            @Override
            public void enqueue(okhttp3.Callback responseCallback) {}

            @Override
            public void cancel() {}

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Request request() {
                return mockRequest;
            }

            @Override
            public okhttp3.Call clone() {
                return null;
            }

            @Override
            public okhttp3.Call.Timeout timeout() {
                return null;
            }
        });

        when(mockResponse.code()).thenReturn(200);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockResponseBody.string()).thenReturn("{\"data\":\"success\"}");

        String result = ResponseHandler.handleResponse(mockRequest, false, mockProxy);
        assertEquals("{\"data\":\"success\"}", result);
    }

    @Test
    public void testHandleResponseClientError() throws IOException {
        when(mockClient.newCall(mockRequest)).thenReturn(new okhttp3.Call() {
            @Override
            public Response execute() throws IOException {
                return mockResponse;
            }

            @Override
            public void enqueue(okhttp3.Callback responseCallback) {}

            @Override
            public void cancel() {}

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Request request() {
                return mockRequest;
            }

            @Override
            public okhttp3.Call clone() {
                return null;
            }

            @Override
            public okhttp3.Call.Timeout timeout() {
                return null;
            }
        });

        when(mockResponse.code()).thenReturn(400);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockResponseBody.string()).thenReturn("{\"code\":-1,\"msg\":\"error\"}");

        assertThrows(BinanceClientException.class, () -> ResponseHandler.handleResponse(mockRequest, false, mockProxy));
    }

    @Test
    public void testHandleResponseServerError() throws IOException {
        when(mockClient.newCall(mockRequest)).thenReturn(new okhttp3.Call() {
            @Override
            public Response execute() throws IOException {
                return mockResponse;
            }

            @Override
            public void enqueue(okhttp3.Callback responseCallback) {}

            @Override
            public void cancel() {}

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Request request() {
                return mockRequest;
            }

            @Override
            public okhttp3.Call clone() {
                return null;
            }

            @Override
            public okhttp3.Call.Timeout timeout() {
                return null;
            }
        });

        when(mockResponse.code()).thenReturn(500);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockResponseBody.string()).thenReturn("{\"error\":\"server error\"}");

        assertThrows(BinanceServerException.class, () -> ResponseHandler.handleResponse(mockRequest, false, mockProxy));
    }

    @Test
    public void testHandleResponseIOException() throws IOException {
        when(mockClient.newCall(mockRequest)).thenReturn(new okhttp3.Call() {
            @Override
            public Response execute() throws IOException {
                throw new IOException("Network error");
            }

            @Override
            public void enqueue(okhttp3.Callback responseCallback) {}

            @Override
            public void cancel() {}

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Request request() {
                return mockRequest;
            }

            @Override
            public okhttp3.Call clone() {
                return null;
            }

            @Override
            public okhttp3.Call.Timeout timeout() {
                return null;
            }
        });

        assertThrows(BinanceConnectorException.class, () -> ResponseHandler.handleResponse(mockRequest, false, mockProxy));
    }

    @Test
    public void testHandleResponseConnectException() throws IOException {
        when(mockClient.newCall(mockRequest)).thenReturn(new okhttp3.Call() {
            @Override
            public Response execute() throws IOException {
                throw new ConnectException("Connection error");
            }

            @Override
            public void enqueue(okhttp3.Callback responseCallback) {}

            @Override
            public void cancel() {}

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Request request() {
                return mockRequest;
            }

            @Override
            public okhttp3.Call clone() {
                return null;
            }

            @Override
            public okhttp3.Call.Timeout timeout() {
                return null;
            }
        });

        assertThrows(BinanceConnectorException.class, () -> ResponseHandler.handleResponse(mockRequest, false, mockProxy));
    }

    @Test
    public void testHandleResponseUnknownHostException() throws IOException {
        when(mockClient.newCall(mockRequest)).thenReturn(new okhttp3.Call() {
            @Override
            public Response execute() throws IOException {
                throw new UnknownHostException("Unknown host");
            }

            @Override
            public void enqueue(okhttp3.Callback responseCallback) {}

            @Override
            public void cancel() {}

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Request request() {
                return mockRequest;
            }

            @Override
            public okhttp3.Call clone() {
                return null;
            }

            @Override
            public okhttp3.Call.Timeout timeout() {
                return null;
            }
        });

        assertThrows(BinanceConnectorException.class, () -> ResponseHandler.handleResponse(mockRequest, false, mockProxy));
    }
}
