
package com.binance.connector.client.impl.spot;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class C2C_listUserOrderHistoryTest {

    private C2C c2c;
    private RequestHandler mockRequestHandler;

    @Before
    public void setUp() {
        mockRequestHandler = mock(RequestHandler.class);
        c2c = new C2C("https://api.binance.com", "apiKey", "secretKey", false, null) {
            @Override
            public RequestHandler getRequestHandler() {
                return mockRequestHandler;
            }
        };
    }

    @Test
    public void testListUserOrderHistory() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("tradeType", "BUY");

        when(mockRequestHandler.sendSignedRequest(anyString(), anyString(), anyMap(), eq(HttpMethod.GET), eq(false)))
                .thenReturn("success");

        String result = c2c.listUserOrderHistory(parameters);

        assertEquals("success", result);
        verify(mockRequestHandler).sendSignedRequest("https://api.binance.com", "/sapi/v1/c2c/orderMatch/listUserOrderHistory", parameters, HttpMethod.GET, false);
    }
}
