
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.MockWebServerDispatcher;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

public class Margin_cancelOcoOrderTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url("/").toString();
    }

    @Test
    public void testCancelOcoOrderSuccess() {
        String path = "/sapi/v1/margin/orderList?symbol=BNBUSDT";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher("/", path, "{\"success\":true}", HttpMethod.DELETE, 200);
        mockWebServer.setDispatcher(dispatcher);

        Margin margin = new Margin(baseUrl, "apiKey", "secretKey", false, null);
        String result = margin.cancelOcoOrder(parameters);
        assertEquals("{\"success\":true}", result);
    }

    @Test
    public void testCancelOcoOrderMissingSymbol() {
        Map<String, Object> parameters = new LinkedHashMap<>();

        Margin margin = new Margin(baseUrl, "apiKey", "secretKey", false, null);
        assertThrows(BinanceConnectorException.class, () -> margin.cancelOcoOrder(parameters));
    }
}
